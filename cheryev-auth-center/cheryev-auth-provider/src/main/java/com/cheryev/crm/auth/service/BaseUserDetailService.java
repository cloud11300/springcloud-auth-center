package com.cheryev.crm.auth.service;

import com.cheryev.crm.auth.mapper.*;
import com.cheryev.crm.auth.pojo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;


/**
 *
 */
public abstract class BaseUserDetailService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected BaseUserHandleMapper baseUserHandleMapper;
    @Autowired
    private BaseRoleHandleMapper baseRoleHandleMapper;
    @Autowired
    private BaseModuleResourcesHandleMapper baseModuleResourcesHandleMapper;

    @Autowired
    private RedisTemplate<String, BaseRoleVO> redisTemplate;
    @Autowired
    private RedisTemplate<String, BaseModuleResourcesVO> resourcesTemplate;

    @Override
    public UserDetails loadUserByUsername(String var1) throws UsernameNotFoundException {

        BaseUserVO baseUser = getUser(var1);

        //查询角色
        List<BaseRoleVO> roles = getRoles(baseUser.getUserId());
        if(roles == null || roles.size()<=0) {
            logger.error("查询角色失败！");
            roles = new ArrayList<>();
        }
        //查询菜单
        List<BaseModuleResourcesVO> baseModuleResourceListResponseData = getMenus(baseUser.getUserId());

        // 获取用户权限列表
        List<GrantedAuthority> authorities = convertToAuthorities(baseUser, roles);

        // 存储菜单到redis
        if( baseModuleResourceListResponseData != null && baseModuleResourceListResponseData.size()>0){
            resourcesTemplate.delete(baseUser.getUserId() + "-Resource");
            baseModuleResourceListResponseData.forEach(e -> {
                resourcesTemplate.opsForList().leftPush(baseUser.getUserId() + "-Resource", e);
            });
        }

        // 返回带有用户权限信息的User
        org.springframework.security.core.userdetails.User user =  new org.springframework.security.core.userdetails.User(baseUser.getUserName(),
                baseUser.getPassWord(), isStatus(baseUser.getStatus()), true, true, true, authorities);
        return new BaseUserDetail(baseUser, user);
    }

    protected abstract BaseUserVO getUser(String var1) ;

    private boolean isStatus(byte status){
        return status == 1 ? true : false;
    }

    private List<BaseRoleVO> getRoles(Integer userId){
        List<BaseRoleVO> roles = baseRoleHandleMapper.selectRoleByUserId(userId);
        roles.forEach(role->{
            List<BaseModuleResourcesVO> resourcesList = baseModuleResourcesHandleMapper.selectResourcesByRoleId(role.getRoleId());
            resourcesList.forEach(resources->{
                if (Constant.MODULE_REPSPIRCES_TYPE_MENU.equals(resources.getModuleType())){//菜单
                    BaseModuleMenuVO baseModuleMenu = baseModuleResourcesHandleMapper.selectMenuByMenuId(resources.getModuleId());
                    resources.setModuleMenu(baseModuleMenu);
                }else if (Constant.MODULE_REPSPIRCES_TYPE_OPERATION.equals(resources.getModuleType())){//操作权限
                    BaseModuleOperationVO baseModuleOperation = baseModuleResourcesHandleMapper.selectOperationByOperationId(resources.getModuleId());
                    resources.setModuleOperation(baseModuleOperation);
                }else if (Constant.MODULE_REPSPIRCES_TYPE_API.equals(resources.getModuleType())){//接口API
                    BaseModuleApisVO baseModuleApis = baseModuleResourcesHandleMapper.selectApisByApiId(resources.getModuleId());
                    resources.setModuleApi(baseModuleApis);
                }
            });
            role.setModules(resourcesList);
        });
        return roles;
    }

    private List<BaseModuleResourcesVO> getMenus(Integer userId){
        List<BaseModuleResourcesVO> resourcesList = baseModuleResourcesHandleMapper.selectResourcesByUserId(userId);
        return resourcesList;
    }

    private List<GrantedAuthority> convertToAuthorities(BaseUserVO baseUser, List<BaseRoleVO> roles) {
        List<GrantedAuthority> authorities = new ArrayList();
        // 清除 Redis 中用户的角色
        redisTemplate.delete(baseUser.getUserId().toString()+"-Role");
        roles.forEach(e -> {
            // 存储用户、角色信息到GrantedAuthority，并放到GrantedAuthority列表
            GrantedAuthority authority = new SimpleGrantedAuthority(e.getRoleId().toString());
            authorities.add(authority);
            //存储角色到redis
            redisTemplate.opsForList().rightPush(baseUser.getUserId().toString()+"-Role", e);
        });
        return authorities;
    }
}
