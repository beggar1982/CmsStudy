<template>
  <div class="albuminfo">
    <el-dialog title="报名信息" :visible.sync="dialogFormVisible" width="30%" center>
      <el-form :model="form">
        <el-form-item label="姓  名" :label-width="formLabelWidth" prop="userName">
          <el-input placeholder="请输入姓名" v-model="form.userName" auto-complete="off" style="width:formEditWidth"></el-input>
        </el-form-item>
        <el-form-item label="课程名称" :label-width="formLabelWidth">
          <el-input placeholder="请输入课程名称" v-model="form.courseName" auto-complete="off" style="width:formEditWidth"></el-input>
        </el-form-item>
        <el-form-item label="手机号码" :label-width="formLabelWidth">
          <el-input placeholder="请输入手机号码" v-model="form.telephone" auto-complete="off" style="width:formEditWidth"></el-input>
        </el-form-item>
        <el-form-item label="邮  箱" :label-width="formLabelWidth">
          <el-input placeholder="请输入联系邮箱" v-model="form.email" auto-complete="off" style="width:formEditWidth"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="reportCourse">报 名</el-button>
      </div>
    </el-dialog>

    <div class="w_950">
      <!-- 左侧专辑图片 S -->
      <div class="video_vdo">
        <img src="http://i1.umivi.net/v/album/2017-09/20170909010014.jpg" tagname="IMG" width="400" height="300">
      </div>
      <!-- 左侧专辑图片 S -->

      <!-- 右侧专辑信息 S -->
      <div class="video_info">
        <h1>{{courseData.data.title}}</h1>
        <div class="video_infowrap">

          <div class="albcomp" id="vip_btn">
            <p style="display:block;">
              <a class="btn_look" title="我要报名" href="javascript:void(0)" style="" @click="dialogFormVisible = true">我要报名</a>
            </p>
          </div>

          <em class="zshymfgk">
            <div class="jrhymfk" style="display:none;">
            </div>
            <div id="video_info_list">
              <p class="abline"></p>
              <dl>
                <dt>讲师：</dt>
                <dd>
                  <span><a target="_blank" href="javascript:void(0)">{{teacherData.data.teacherName}}</a></span>
                  <span class="jstitle">{{teacherData.data.teacherTitle}}</span>
                </dd>
              </dl>
              <dl>
                <dt>时间：</dt><dd><span>{{courseData.data.shortDesc}}</span>
              </dd>
              </dl>
              <!--<dl class="gkplyy">-->
                <!--<dt>课程简介：</dt>-->
                <!--<dd><strong>{{courseData.data.description}}</strong></dd>-->
              <!--</dl>-->
            </div>
          </em>

        </div>
        <em class="zshymfgk">
        </em>
      </div>
      <em class="zshymfgk">
        <!-- 右侧专辑信息 S -->
      </em>
    </div>
    <em class="zshymfgk"></em>

    <div class="w_640">
      <!-- 讲师介绍 S -->
      <div class="jsjsbox">
        <h2><span>讲师介绍</span></h2>

        <div class="tcherinfo">
          <a class="img_l" target="_blank" href="javascript:void(0)">
            <img src="http://i1.umivi.net/v/teacher/avatar/136.jpg">
          </a>
          <h3>{{teacherData.data.teacherName}}</h3>
          <p class="gray9">{{teacherData.data.teacherTitle}}</p>

          <p class="tchertxt">{{teacherData.data.teacherDesc}}</p>

        </div>
      </div>

      <!-- 课程介绍 S -->
      <div class="kcjsbox">
        <h2><span class="t_kcjs">课程介绍</span></h2>
        <p class="kcjstxt">
          {{courseData.data.description}}
        </p>
      </div>
    </div>

  </div>
</template>

<script>
  import {categoryApi} from "@/service/api";
  import {courseApi} from "@/service/api";
  import {teacherApi} from "@/service/api";

  export default {
    name: "coursedetail",

    data() {
      return {
        courseData: [],
        teacherData: [],

        dialogFormVisible: false,
        form: {
          userName: '',
          courseName: '',
          telephone: '',
          email: '',
        },
        formLabelWidth: '120px',
        formEditWidth: '400px',
      }
    },
    mounted () {
      let id = this.$route.query.id;
      this.queryCourseDetail(id);
    },
    methods: {
      reportCourse() {
        if (!this.form.userName) {
          alert('请输入姓名!')
          return;
        }
        if (!this.form.courseName) {
          alert('请输入课程名称!')
          return;
        }
        if (!this.form.telephone) {
          alert('请输入手机号码!')
          return;
        }
        if (!this.form.email) {
          alert('请输入联系邮箱!')
          return;
        }

        const param = {'userName': this.form.userName, 'courseName': this.form.courseName, 'telephone': this.form.telephone, 'email': this.form.email}
        courseApi.getCourseDetail(param)
          .then((data) => {
            this.courseData = data;
            console.log(data);

            const teacherParam = {'id': this.courseData.data.teacherId}
            teacherApi.getTeacherInfo(teacherParam)
            .then((info) => {
              this.teacherData = info;
              console.log(info);
            })
            .catch((error) => {
              console.error(error);
            })
          })
          .catch((error) => {
            console.error(error);
          })

        this.dialogFormVisible = false;
      },
      queryCourseDetail(id) {
        const param = {'id': id}
        courseApi.getCourseDetail(param)
          .then((data) => {
            this.courseData = data;
            console.log(data);

            const teacherParam = {'id': this.courseData.data.teacherId}
            teacherApi.getTeacherInfo(teacherParam)
              .then((info) => {
                this.teacherData = info;
                console.log(info);
              })
              .catch((error) => {
                console.error(error);
              })
          })
          .catch((error) => {
            console.error(error);
          })
      },
    }
  }
</script>

<style scoped>
  @import "../common/coursedetail.css";
</style>
