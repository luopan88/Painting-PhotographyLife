<template>
  <div id="app">
	  <el-row :gutter="20" type="flex" justify="center" align="middle">
		  <el-col :sm="15">
			  <h1 class="title">绘影生活</h1>
			  <p class="titlecontext">
				  绘影生活是一个可以供您发布分享您的绘画或摄影创作的平台，您可以通过该平台结识更多共同爱好者，并与他们一起分享讨论。
			  </p>
			  
			  <el-row type="flex" justify="center">
			  	<el-col :xs="12" class="col">
					<el-button class="mybtn">立即进站</el-button>
			  	</el-col>
			  	<el-col :xs="12" :sm="0" class="col">
			  		<el-button class="mybtn"  @click="dialogFormVisible = true">
			  			登录
			  			<i class="el-icon-arrow-right"></i>
			  		</el-button>
			  	</el-col>
			  </el-row>
		  </el-col>
		  <el-col :sm="9" :xs="0" class="col">
			  <Login></Login>
		  </el-col>
		  
	  </el-row>
	  
		
		<el-dialog title="登录" :visible.sync="dialogFormVisible" 
				:fullscreen="true" 
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
		}
	}
</script>

<style>
	.titlecontext{
		font-size: 20px;
		font-weight: 400;
		color: hsla(0,0%,100%,.6);
		text-indent: 2rem;
	}
	.dialog{
		background-color: transparent;
	}
	.changebut{
		display: block;
		margin: auto;
		margin-top: 20px;
		
	}

	.title{
		font-size: 64px;
		display: block;
		text-align: center;
		font-weight: 500;
		line-height: 64px;
		color: #EBEEF5;
		/* font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif; */
	}
	#app {
		background-color: transparent;
		position: absolute;
		top: 50%;
		left: 50%;
		transform: translate(-50%, -50%);
		width: 70%;
		max-width: 1020px;
		    font-family: "Microsoft YaHei", "Microsoft Sans Serif", "Microsoft SanSerf", "\5FAE\8F6F\96C5\9ED1";
	}
</style>
