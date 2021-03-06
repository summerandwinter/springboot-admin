package com.bmsoft.system.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.bmsoft.common.domain.Tree;
import com.bmsoft.common.service.impl.BaseService;
import com.bmsoft.common.util.TreeUtils;
import com.bmsoft.system.dao.MenuMapper;
import com.bmsoft.system.domain.Menu;
import com.bmsoft.system.service.MenuService;
import com.bmsoft.system.service.RoleMenuServie;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service("menuService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class MenuServiceImpl extends BaseService<Menu> implements MenuService {

	@Autowired
	private MenuMapper menuMapper;

	@Autowired
	private RoleMenuServie roleMenuService;

	@Override
	public List<Menu> findUserPermissions(String userName) {
		return this.menuMapper.findUserPermissions(userName);
	}

	@Override
	public List<Menu> findUserMenus(String userName) {
		return this.menuMapper.findUserMenus(userName);
	}

	@Override
	public List<Menu> findAllMenus(Menu menu) {
		try {
			Example example = new Example(Menu.class);
			Criteria criteria = example.createCriteria();
			if (StringUtils.isNotBlank(menu.getMenuName())) {
				criteria.andCondition("menu_name=", menu.getMenuName());
			}
			if (StringUtils.isNotBlank(menu.getType())) {
				criteria.andCondition("type=", Long.valueOf(menu.getType()));
			}
			example.setOrderByClause("menu_id");
			return this.selectByExample(example);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	@Override
	public Tree<Menu> getMenuButtonTree() {
		List<Tree<Menu>> trees = new ArrayList<>();
		List<Menu> menus = this.findAllMenus(new Menu());
		buildTrees(trees, menus);
		return TreeUtils.build(trees);
	}

	@Override
	public Tree<Menu> getMenuTree() {
		List<Tree<Menu>> trees = new ArrayList<>();
		Example example = new Example(Menu.class);
		example.createCriteria().andCondition("type =", 0);
		example.setOrderByClause("create_time");
		List<Menu> menus = this.selectByExample(example);
		buildTrees(trees, menus);
		return TreeUtils.build(trees);
	}

	private void buildTrees(List<Tree<Menu>> trees, List<Menu> menus) {
		for (Menu menu : menus) {
			Tree<Menu> tree = new Tree<>();
			tree.setId(menu.getMenuId().toString());
			tree.setParentId(menu.getParentId().toString());
			tree.setText(menu.getMenuName());
			trees.add(tree);
		}
	}

	@Override
	public Tree<Menu> getUserMenu(String userName) {
		List<Tree<Menu>> trees = new ArrayList<>();
		List<Menu> menus = this.findUserMenus(userName);
		for (Menu menu : menus) {
			Tree<Menu> tree = new Tree<>();
			tree.setId(menu.getMenuId().toString());
			tree.setParentId(menu.getParentId().toString());
			tree.setText(menu.getMenuName());
			tree.setIcon(menu.getIcon());
			tree.setUrl(menu.getUrl());
			trees.add(tree);
		}
		return TreeUtils.build(trees);
	}

	@Override
	public Menu findByNameAndType(String menuName, String type) {
		Example example = new Example(Menu.class);
		example.createCriteria().andCondition("lower(menu_name)=", menuName.toLowerCase()).andEqualTo("type",
				Long.valueOf(type));
		List<Menu> list = this.selectByExample(example);
		if (list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	@Transactional
	public void addMenu(Menu menu) {
		menu.setCreateTime(new Date());
		if (menu.getParentId() == null)
			menu.setParentId(0L);
		this.save(menu);
	}

	@Override
	@Transactional
	public void deleteMeuns(String menuIds) {
		List<String> list = Arrays.asList(menuIds.split(","));
		this.batchDelete(list, "menuId", Menu.class);
		this.roleMenuService.deleteRoleMenusByMenuId(menuIds);
		this.menuMapper.changeToTop(list);
	}

	@Override
	public Menu findById(Long menuId) {
		return this.selectByKey(menuId);
	}

	@Override
	@Transactional
	public void updateMenu(Menu menu) {
		menu.setModifyTime(new Date());
		if (menu.getParentId() == null)
			menu.setParentId(0L);
		this.updateNotNull(menu);
	}

}
