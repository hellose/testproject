export default {
  name: "TreeMenu",
  props: {
    propMenuObj: Object,
  },
  data() {
    return {
      isOpen: true,
    };
  },
  computed: {
    isFolder() {
      return this.propMenuObj.childMenus && this.propMenuObj.childMenus.length;
    },
  },
  methods: {
    toggle() {
      // console.log("toggle()");
      this.isOpen = !this.isOpen;
    },
    setRootComponentEditMenu(e) {
      // console.log("setRootComponentEditMenu()");

      // 1. root component의 editMenu 객체

      // v-model에러로 인해 parentMenu는 항상 있어야함
      const tempEditMenu = { parentMenu: {} };

      const thisProp = this.$props.propMenuObj;
      tempEditMenu.menuId = thisProp.menuId;
      tempEditMenu.menuName = thisProp.menuName;
      tempEditMenu.menuUrl = thisProp.menuUrl;
      tempEditMenu.menuOrder = thisProp.menuOrder;
      tempEditMenu.useState = thisProp.useState;

      // 루트 메뉴가 아닌 경우에만 상위 메뉴를 셋팅
      if (!(this.$parent === this.$root)) {
        // if (this.$parent.$props.propMenuObj) 과 동일
        tempEditMenu.parentMenu.menuId = this.$parent.$props.propMenuObj.menuId;
      }
      this.$root.editMenu = tempEditMenu;
      
      // 2. root component의 addMenu 객체
      const tempAddMenu = { useState:'Y', parentMenu: {} };
      tempAddMenu.parentMenu.menuId = this.$props.propMenuObj.menuId;
      this.$root.addMenu = tempAddMenu;
      
      e.stopPropagation(); // prevent calling toggle() method
    },
  },

  template: `
	 	<li>
        	<div @click="toggle()">
          		<span v-if="isFolder">{{ isOpen ? '[-] ' : '[+] ' }}</span>
				      <span @click="setRootComponentEditMenu">{{ propMenuObj.menuName }}</span>
        	</div>
        	<ul v-if="isFolder" v-show="isOpen">
        		<tree-menu v-for="(menu, index) in propMenuObj.childMenus" :key="menu.menuId" :prop-menu-obj="menu"></tree-menu>
        	</ul>
		</li>
	`,
};
