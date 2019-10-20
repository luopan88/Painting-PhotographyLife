<template>
	<el-form size="mini" label-width="60px" label-position="top" class="form">
	  <el-form-item label="账号:">
		<el-input size="medium" class="aaa"></el-input>
	  </el-form-item>
	  <el-form-item label="密码:">
		  <el-input show-password></el-input>
	  </el-form-item>
	  
	  <el-form-item label="验证码:">
		<el-row :gutter="20" type="flex">
			<el-col :span="12">
				<el-input ></el-input>
			</el-col>
			<el-col :span="12">
					<el-image
						style="width: 75px; height: 29px"
						:src= "imgurl" 
						fit="fill" 
						title="点击图片刷新"></el-image>
			</el-col>
		</el-row>
		</el-form-item>
		<el-form-item>
				  <el-button type="primary" class="but">登录</el-button>
		</el-form-item>
		<el-form-item>
				  <el-button class="but">创建</el-button>
		</el-form-item>
	</el-form>
</template>

<script>
	export default{
		data(){
			return {
				imgurl: 'https://static.oschina.net/uploads/img/201712/16113708_B8Hu.png'
			}
		}
	}
</script>

<style>
	.but{
		display: block;
		width: 100%;
		margin: 0 auto;
	}
	.form{
		/* background-color: #EBEEF5; */
		background-color: rgba(235,238,245, .7);
		max-width: 300px;
		min-width: 200px;
		padding: 24px;
		border-radius: 4px;
		box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
		margin: auto;
	}
</style>
