<template>
  <div id="app">
	  <el-row :gutter="20" type="flex" justify="center">
		  <transition name="el-zoom-in-center">
			  <el-col :sm="15">
				  <h1 class="title">绘影生活</h1>
				  <p>
					  aesf qwr gwrga 
					  erg aergq awhrqa whqeht
					  qae hqrhqr hqerg qwre hgqwrhg qer herh
				  </p>
			  </el-col>
		  </transition>
		  <transition name="el-zoom-in-center">
			  <el-col :sm="9" :xs="0" class="col">
				  <Login></Login>
			  </el-col>
		  </transition>
		  
	  </el-row>
	  
		<el-row type="flex" justify="center">
			<el-col :sm="0" class="col">
				<el-button class="changebut" size="medium" @click="dialogFormVisible = true">
					登录页
					<i class="el-icon-arrow-right"></i>
				</el-button>
			</el-col>
		</el-row>
		
		<el-dialog title="登录" :visible.sync="dialogFormVisible" 
				fullscreen="true" 
				custom-class="dialog"
				append-to-body>
					  <Login></Login>
		</el-dialog>
  </div>
</template>

<script>
	import Login from './component/Login.vue'
export default {
	components:{
		Login
	},
	data(){
		return {
			dialogFormVisible: false
		}
	},
	computed:{
		
	},methods:{
		
	}
}
</script>

<style>
	.dialog{
		background-color: transparent;
	}
	.changebut{
		display: block;
		margin: auto;
		margin-top: 20px;
		
	}

/* 	.el-input{
		min-width: 120px;
	} */
	.title{
		font-size: 64px;
		display: block;
		/* text-align: center; */
		text-align: left;
		font-weight: 500;
		line-height: 64px;
		color: #EBEEF5;
		font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
	}
	#app {
		/* width: 300px;
		height: 200px; */
		background-color: transparent;
		border-radius: 10px;
		position: absolute;
		top: 50%;
		left: 50%;
		transform: translate(-50%, -50%);
		/* box-sizing: border-box; */
		width: 70%;
		max-width: 1020px;
		/* opacity: 0.5; */
	}
</style>
