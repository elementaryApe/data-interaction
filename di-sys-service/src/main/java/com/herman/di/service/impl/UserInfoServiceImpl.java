package com.herman.di.service.impl;

import com.herman.di.dao.ArticleInfoRepository;
import com.herman.di.dao.RoleRepository;
import com.herman.di.dao.UserDetailsRepository;
import com.herman.di.dao.UserInfoRepository;
import com.herman.di.entity.ArticleInfo;
import com.herman.di.entity.Role;
import com.herman.di.entity.UserDetails;
import com.herman.di.entity.UserInfo;
import com.herman.di.entity.vo.UserInfoVo;
import com.herman.di.service.UserInfoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户接口实现
 *
 * @author hsh
 * @create 2018-08-29 15:59
 **/
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoRepository userInfoRepository;

    @Resource
    private UserDetailsRepository userDetailsRepository;

    @Resource
    private ArticleInfoRepository articleInfoRepository;

    @Resource
    private RoleRepository roleRepository;


    public UserInfo getUserInfoById(String userId) throws Exception {
        UserInfo one = userInfoRepository.findById(Integer.valueOf(userId));
        return one;
    }

    public boolean addUserInfo(UserInfo userInfo) throws Exception {
        UserInfo save = userInfoRepository.save(userInfo);
        if (save != null && save.getId() != null)
            return true;
        return false;
    }

    public boolean addArticleInfo(ArticleInfo articleInfo) throws Exception {
        ArticleInfo save = articleInfoRepository.save(articleInfo);
        if (save != null && save.getId() != null)
            return true;
        return false;
    }

    public boolean addRole(Role role) throws Exception {
        Role save = roleRepository.save(role);
        if (save != null && save.getId() != null)
            return true;
        return false;
    }

    /**
     * 动态查询
     *
     * @param query 查询条件
     * @return List<UserInfo>
     * @throws Exception
     */
    public List<UserInfo> getUserInfoList(final UserInfoVo query) throws Exception {
        return userInfoRepository.findAll(getSpecification(query), new Sort(Sort.Direction.DESC, new String[]{"id"}));
    }

    /**
     * 分页接口
     */
    public Page<UserInfo> findUserInfo(final UserInfoVo userInfo, PageRequest request) throws Exception {
        //1 原生分页 查询条件不能为空
        //  userInfoRepository.findByUNameContainingAndUNumberEqualsAndIdEquals(query.getUName(),query.getUNumber(),query.getId(),request);
        //2 动态查询
        return userInfoRepository.findAll(getSpecificationByMultiCondition(userInfo), request);
    }

    private Specification getSpecification(final UserInfoVo userInfo) {
        return new Specification<UserInfo>() {
            public Predicate toPredicate(Root<UserInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = null;
                if (userInfo != null && userInfo.getId() != null)
                    predicate = cb.equal(root.<Integer>get("id"), userInfo.getId());
                if (userInfo != null && userInfo.getUName() != null)
                    predicate = cb.like(root.<String>get("uName"), "%" + userInfo.getUName() + "%");
                if (userInfo != null && userInfo.getUNumber() != null)
                    predicate = cb.equal(root.<String>get("uNumber"), userInfo.getUNumber());
                if (userInfo != null && userInfo.getAddress() != null)
                    predicate = cb.like(root.<UserDetails>get("userDetails").<String>get("address"), "%" + userInfo.getAddress() + "%");
                if (predicate != null)
                    return query.where(predicate).getRestriction();
                return null;
            }
        };
    }

    /**
     * 关联多条件查询
     *
     * @param userInfo
     * @return
     */
    private Specification getSpecificationByMultiCondition(final UserInfoVo userInfo) {
        return new Specification<UserInfo>() {
            public Predicate toPredicate(Root<UserInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                if (userInfo != null && userInfo.getIds() != null) {
                    String[] ids = userInfo.getIds().split(",");
                    CriteriaBuilder.In<Integer> in = cb.in(root.<Integer>get("id"));
                    for (String id : ids) {
                        in.value(Integer.valueOf(id));
                    }
                    predicates.add(in);
//                    predicates.add(cb.equal(root.<Integer>get("id"), userInfo.getId()));
                }
                if (userInfo != null && userInfo.getUName() != null)
                    predicates.add(cb.like(root.<String>get("uName"), "%" + userInfo.getUName() + "%"));
                if (userInfo != null && userInfo.getUNumber() != null)
                    predicates.add(cb.equal(root.<String>get("uNumber"), userInfo.getUNumber()));
                if (userInfo != null && userInfo.getAddress() != null)
                    predicates.add(cb.like(root.<UserDetails>get("userDetails").<String>get("address"), "%" + userInfo.getAddress() + "%"));
//                    Join<UserInfo, UserDetails> userDetails = root.join("userDetails", JoinType.LEFT);
//                    predicates.add(cb.like(userDetails.<String>get("address"), "%" + userInfo.getAddress() + "%"));

                if (predicates.size() > 0)
                    return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
                return null;
            }
        };
    }

    public UserDetails getUserDetails(Integer userId) throws Exception {
        return userDetailsRepository.findByUserInfo(new UserInfo(userId));
    }

    public List<ArticleInfo> getArticleInfo(Integer userId) throws Exception {
        return articleInfoRepository.findByUserInfo(new UserInfo(userId));
    }

    public List<Role> getRoleInfo(String[] userIds) throws Exception {
        List<UserInfo> userInfos = new ArrayList<UserInfo>();
        for (String id : userIds)
            userInfos.add(new UserInfo(Integer.valueOf(id)));
        return roleRepository.findByUserInfoList(userInfos);
    }

    public Role getRole(Integer roleId) throws Exception {
        return roleRepository.findOne(roleId);
    }
}
