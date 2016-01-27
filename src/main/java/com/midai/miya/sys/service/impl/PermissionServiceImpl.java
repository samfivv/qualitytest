package com.midai.miya.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midai.miya.easyui.Tree;
import com.midai.miya.sys.dao.PermissionDao;
import com.midai.miya.sys.model.Permission;
import com.midai.miya.sys.model.RolePermission;
import com.midai.miya.sys.service.PermissionService;

@Service("permissionServiceImpl")
public class PermissionServiceImpl implements PermissionService{
	@Autowired
	private PermissionDao permissionDao;
	@Override
	public List<Tree> findPermissionByOperatorId(String operatorId) {
		List<Permission> resources=permissionDao.findPermissionByOperatorId(operatorId);
		List<Tree> tree = new ArrayList<Tree>();
		Tree node1=new Tree();
		node1.setPermissionIsshow(1);
		node1.setPermissionType(1);
		node1.setPermissionSort(0);
		node1.setText("后台管理系统");
		node1.setId("0");
		node1.setState("open");
		tree.add(node1);
		for (Permission permission : resources) {
			Tree node = new Tree();
			node.setText(permission.getPermissionName());
			node.setIconCls(permission.getPermissionImgUrl());
			node.setId(permission.getPermissionId());
			node.setPid(permission.getPermissionParentId());
			node.setPermissionSort(permission.getPermissionSort());
			node.setPermissionUrl(permission.getPermissionUrl());
			node.setPermissionType(permission.getPermissionType());
			node.setPermissionIsshow(permission.getPermissionIsshow());
			node.setState("open");
			Map<String, String> attributes = new HashMap<String, String>();
			attributes.put("url", permission.getPermissionUrl());
			//attributes.put("target", resource.getTarget());
			node.setAttributes(attributes);
			tree.add(node);
		}
	
		return tree;
	}
	@Override
	public List<Tree> findAllPermission() {
		List<Permission> resources=permissionDao.findAllPermission();
		List<Tree> tree = new ArrayList<Tree>();
		for (Permission permission : resources) {
			Tree node = new Tree();
			node.setText(permission.getPermissionName());
			node.setIconCls(permission.getPermissionImgUrl());
			node.setId(permission.getPermissionId());
			node.setPid(permission.getPermissionParentId());
			node.setState("open");
			Map<String, String> attributes = new HashMap<String, String>();
			attributes.put("url", permission.getPermissionUrl());
			//attributes.put("target", resource.getTarget());
			node.setAttributes(attributes);
			tree.add(node);
		}
	
		return tree;
	}
	@Override
	public List<Tree> findPermissionByRoleId(String roleId) {
		List<Permission> resources=permissionDao.findPermissionByRoleId(roleId);
		List<Tree> tree = new ArrayList<Tree>();
		for (Permission permission : resources) {
			Tree node = new Tree();
			node.setText(permission.getPermissionName());
			node.setIconCls(permission.getPermissionImgUrl());
			node.setId(permission.getPermissionId());
			node.setPid(permission.getPermissionParentId());
			node.setState("open");
			Map<String, String> attributes = new HashMap<String, String>();
			attributes.put("url", permission.getPermissionUrl());
			node.setAttributes(attributes);
			tree.add(node);
		}
	
		return tree;
	}
	@Override
	public void deletePermissionByRoleId(String roleId) {
		permissionDao.delete(roleId);
	}
	@Override
	public void addPermission(RolePermission rolePermission) {
		permissionDao.add(rolePermission);
	}


     @Override
     public List<Permission> findPermission(){
        List<Permission> Permission = permissionDao.findPermission();
        return Permission;
     }

     @Override
     public void savePermission(Permission permission){
        permissionDao.addPermission(permission);
     }

     @Override
     public Permission findPermissionById(String permissionId){
        Permission Permission = permissionDao.findPermissionById(permissionId);
        return Permission;
     }

     @Override
     public void deletePermissionById(String permissionId){
        permissionDao.deletePermissionById(permissionId);
     }


     @Override
     public void updatePermissionById(Permission permission){
        permissionDao.updatePermissionById(permission);
     }

     @Override
     public long selectPermissionById(String permissionId){
        long Permission = permissionDao.selectPermissionById(permissionId);
        return Permission;
     }

     @Override
     public List<Tree> selectPermissionAll(){
    	 List<Permission> resources = permissionDao.selectPermissionAll();
    	 List<Tree> tree = new ArrayList<Tree>();
 		for (Permission permission : resources) {
 			Tree node = new Tree();
 			node.setText(permission.getPermissionName());
 			node.setIconCls(permission.getPermissionImgUrl());
 			node.setId(permission.getPermissionId());
 			node.setPid(permission.getPermissionParentId());
 			node.setPermissionSort(permission.getPermissionSort());
 			node.setPermissionUrl(permission.getPermissionUrl());
 			node.setPermissionType(permission.getPermissionType());
 			node.setPermissionIsshow(permission.getPermissionIsshow());
 			node.setState("open");
 			Map<String, String> attributes = new HashMap<String, String>();
 			attributes.put("url", permission.getPermissionUrl());
 			//attributes.put("target", resource.getTarget());
 			node.setAttributes(attributes);
 			tree.add(node);
 		}
        return tree;
     }
}