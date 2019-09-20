<template>
  <div class="wrap">
    <div class="container">
      <div class="lists_area">
        <div class="list_top_tab">
          <h2 class="list_top_tab_title">{{categoryDatas.data.cateTitle}}</h2>
          <div class="list_top_tab_area">
            <div class="list_top_tab_left">
              <div class="total_all">
                <a href="javascript:void(0)"
                   v-on:click="onAllItemClick(categoryDatas.data.cateId)"
                   :class="{cateItemSelect:selectCateId == 0}">
                  全部
                </a>
              </div>
              <ul class="list_top_tab_left_list">
                <li class="" v-for="cateItem in categoryDatas.data.children">
                  <a href="javascript:void(0)" v-on:click="onCateItemClick(cateItem.id)"
                     :class="{cateItemSelect:selectCateId == cateItem.id}">
                    {{cateItem.text}}
                  </a>
                </li>
              </ul>
            </div>
          </div>
        </div>

        <div class="list_main_area">
          <ul class="list_main_content">

            <li class="list_main_content_detail" v-for="courseItem in courseListData">
              <a href="javascript:void(0)" v-on:click="onCourseDetailClick(courseItem.id)" target="_blank">
                <div class="list_main_content_detail_img">
                  <div class="list_main_content_detail_img_blank">
                    <span class="is_video"></span>
                  </div>
                  <img alt="">
                </div>
                <div class="list_main_content_detail_desc">
                  <p class="list_main_content_detail_desc_title">{{courseItem.title}}</p>
                  <div class="list_main_content_detail_desc_state">
                    <p class="list_main_content_detail_desc_time">{{courseItem.shortDesc}}</p>
                  </div>
                </div>
              </a>
            </li>

          </ul>
        </div>

      </div>
    </div>
  </div>
</template>

<script>
  import {categoryApi} from "@/service/api";
  import {courseApi} from "@/service/api";

  export default {
    name: "courselist",

    data() {
      return {
        categoryDatas:[],
        courseListData: [],
        selectCateId: 0
      }
    },
    mounted() {
      let cateId = this.$route.query.cateid;
      let id = this.$route.query.id;
      this.queryCategoryDatas(cateId);
      if (id == 0) {
        this.onAllItemClick(cateId);
      } else {
        this.onCateItemClick(id);
      }
    },
    methods: {
      queryCategoryDatas(cateId) {
        const parentCateId = {'parentid': cateId}
        categoryApi.getCategoryData(parentCateId)
          .then((data) => {
            this.categoryDatas = data;
            console.log(data);
          })
          .catch((error) => {
            console.error(error);
          })
      },
      onCateItemClick(cateId) {
        this.selectCateId = cateId;
        const cateParam = {'cateId': cateId}
        courseApi.getCourseListInCate(cateParam)
          .then((data) => {
            this.courseListData = data.rows;
            console.log(data);
          })
          .catch((error) => {
            console.error(error);
          })
      },
      onAllItemClick(parentCateId) {
        this.selectCateId = 0;
        const queryParam = {'pCateId': parentCateId}
        courseApi.getAllCourseListInTopLevelCate(queryParam)
          .then((data) => {
            this.courseListData = data.data;
            console.log(data);
          })
          .catch((error) => {
            console.error(error);
          })
      },
      onCourseDetailClick(id) {
        this.$router.push('/coursedetail?id=' + id);
      }
    }
  }
</script>

<style scoped>
  @import "../common/courselist.css";
</style>
