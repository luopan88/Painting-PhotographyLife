<template>
  <div id="app">
	  <el-row :gutter="20" type="flex" justify="center">
		  <transition name="el-zoom-in-center">
			  <el-col :sm="15" :xs="titlexs(true)">
				  <h1 class="title">绘影生活</h1>
				  <p>
					  aesf qwr gwrga 
					  erg aergq awhrqa whqeht
					  qae hqrhqr hqerg qwre hgqwrhg qer herh
				  </p>
			  </el-col>
		  </transition>
		  <transition name="el-zoom-in-center">
			  <el-col :sm="9" :xs="titlexs(false)" class="col">
				  <el-form label-width="60px" label-position="top" class="form">
					  <el-form-item label="账号:">
						<el-input size="medium" class="aaa"></el-input>
					  </el-form-item>
					  <el-form-item label="密码:">
						  <el-input show-password></el-input>
					  </el-form-item>
				  </el-form>
			  </el-col>
		  </transition>
	  </el-row>
	  
		<el-row type="flex" justify="center">
			<el-col :sm="0" class="col">
				<el-button class="changebut" size="medium" @click="changexs">
					<i v-show="!changetotitle" class="el-icon-arrow-left"></i>
					{{changebuttext}}
					<i v-show="changetotitle" class="el-icon-arrow-right"></i>
				</el-button>
			</el-col>
		</el-row>
  </div>
</template>

<script>
export default {
	data(){
		return {
			changetotitle: true,
			changebuttexts: ["登录页","主页"]
		}
	},
	computed:{
		changebuttext(){
			return this.changetotitle ? 
				this.changebuttexts[0] : this.changebuttexts[1]
		}
	},methods:{
		titlexs(x){
			console.log(x)
			console.log(this.changetotitle)
			return this.changetotitle===x ? 24 : 0
		},
		changexs(){
			this.changetotitle = !this.changetotitle
		}
	}
}
</script>

<style>
	.changebut{
		display: block;
		margin: auto;
		margin-top: 20px;
		
	}
	.form{
		background-color: #EBEEF5;
		max-width: 250px;
		min-width: 150px;
		padding: 24px;
		border-radius: 4px;
		box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
		margin: auto;
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
