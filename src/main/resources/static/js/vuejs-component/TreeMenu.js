export default {
	name: 'TreeMenu',
	props: {
		propObj: Object
	},
	data() {
		return {
			isOpen: true
		}
	},
	computed: {
		isFolder(){
			return this.propObj.childMenus && this.propObj.childMenus.length; 
		},
	},
	methods: {
		toggle(){
			this.isOpen = !this.isOpen;
		},
	},

	template: `
	 	<li>
        	<div @click="toggle()">
          		<span v-if="isFolder">{{ isOpen ? '- ' : '+ ' }}</span>{{ propObj.menuName }}
        	</div>
        	<ul v-if="isFolder" v-show="isOpen">
        		<tree-menu v-for="(menu, index) in propObj.childMenus" :key="menu.menuId" :prop-obj="menu"></tree-menu>
        	</ul>
		</li>
	`
}