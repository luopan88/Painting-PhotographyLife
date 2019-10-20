<template>
	<el-form label-width="60px" label-position="top" class="form">
	  <el-form-item label="账号:">
		<el-input size="medium" class="aaa"></el-input>
	  </el-form-item>
	  <el-form-item label="密码:">
		  <el-input show-password></el-input>
	  </el-form-item>
	</el-form>
</template>

<script>
	export default{
		data(){
			return {
				
			}
		}
	}
</script>

<style>
	.form{
		background-color: #EBEEF5;
		max-width: 250px;
		min-width: 150px;
		padding: 24px;
		border-radius: 4px;
		box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
		margin: auto;
	}
</style>
