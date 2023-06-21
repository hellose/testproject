export default {
	name: "ChildComponent",
	
	props: {
		prop: String,
	},
	
	data() {
		return {};
	},
	
	computed: {
		
	},
	
	methods: {
		
	},
	
	created() {
		console.log("Child created()");
	},
	
	mounted() {
		console.log("Child mounted()");
	},
	
	unmounted() {
		console.log("Child unmounted()");
	},

	template: `
		<span>typeof(prop): {{ typeof(prop) }}</span><br>
		<span>실제 출력: {{ prop }}</span><br>
		<br>
	`,
};
