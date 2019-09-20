<template>
  <div>
    <!--首屏-->
    <div class="wrap c1" id="c1">
      <div class="w1200">
        <div class="sortbox" id="sortbox">

          <div class="sort-l">
            <dl v-for="(cateItem, index) in categoryDatas" class="sortTag" index="index"
                :class="{itemSelectbg:selectIndex == index}"
                @mousedown="changeStyle(index)">
              <dt>
              <a href="javascript:void(0)" v-on:click="onCateClick(cateItem.id, 0)">
                {{cateItem.text}}
              </a>
              <i></i>
              </dt>
              <dd>
                <a v-for="item in cateItem.children" href="javascript:void(0)"
                   v-on:click="onCateClick(cateItem.id, item.id)">
                  {{item.text}}
                </a>
              </dd>
            </dl>
          </div>

          <div class="sort-r">
            <div class="sort-list" style="display: block;">
              <div class="sort-t">
                <dl>
                  <dt>
                    <a href="javascript:void(0)" v-on:click="onCateClick(categoryDatas[selectIndex].id, 0)">
                      全部
                    </a>
                  </dt>
                  <dd>
                    <a v-for="item in categoryDatas[selectIndex].children" href="javascript:void(0)"
                       v-on:click="onCateClick(categoryDatas[selectIndex].id, item.id)">
                      {{item.text}}
                    </a>
                  </dd>
                </dl>
              </div>
            </div>
          </div>

        </div>
      </div>
    </div>

    <!--***********************-->

    <!--最新上线-->
    <div class="wrap c2">
      <div class="w1200">
        <div class="new-title">最新上线</div>
        <ul class="ul-imglist newlist">

          <li class="li " v-for="item in newCourseList">
            <a href="javascript:void(0)" v-on:click="onCourseClick(item.id)" class="a-c">
              <div class="li-img">
                <img src="http://i1.umivi.net/2018/1227/1545905967747.jpg" class="img">
                <div class="li-img-bg"><i class="icon-video"></i></div>
              </div>
              <h3>{{item.title}}</h3>
              <div class="li-b">
                <span class="li-b-l" style="width:130px;">{{item.shortDesc}}</span>
              </div>
            </a>
          </li>

        </ul>
      </div>
    </div>

    <!--*****************-->

    <!--精品课程-->
    <div class="boutique">
      <h2 class="h2"><strong>精品课程</strong></h2>
      <ul class="ul-imglist boutiquelist">

        <li class="li " v-for="item in supremeCourseList">
          <a href="javascript:void(0)" v-on:click="onCourseClick(item.id)" class="a-c">
            <div class="li-img">
              <img src="http://i1.umivi.net/2018/1224/1545624887444.png" class="img">
              <div class="li-img-bg"><i class="icon-video"></i></div>
            </div>
            <h3>{{item.title}}</h3>
            <div class="li-b">
              <span class="li-b-l">{{item.shortDesc}}</span>
            </div>
          </a>
        </li>

      </ul>
    </div>

  </div>
</template>

<script>
  import {categoryApi} from "@/service/api";
  import {courseApi} from "@/service/api";

  export default {
    name: "courseindex",

    data() {
      return {
        selectIndex:0,
        categoryDatas:[],
        newCourseList: [],
        supremeCourseList: []
      }
    },
    mounted () {
      this.queryCategoryTree();
      this.queryLatestCourseList();
      this.querySupremeCourseList();
    },
    methods: {
      changeStyle(index) {
        this.selectIndex = index;
      },
      onCateClick(cateid, id) {
        this.$router.push('/courselist?cateid=' + cateid + '&id=' + id)
      },
      onCourseClick(id) {
        this.$router.push('/coursedetail?id=' + id);
      },
      queryCategoryTree() {
        categoryApi.getCategoryTree()
          .then((data) => {
            this.categoryDatas = data;
            console.log(data);
          })
          .catch((error) => {
            console.error(error);
          })
      },
      queryLatestCourseList() {
        courseApi.getLatestCourseList()
          .then((data) => {
            this.newCourseList = data.data;
            console.log(data);
          })
          .catch((error) => {
            console.error(error);
          })
      },
      querySupremeCourseList() {
        courseApi.getSupremeCourseList()
          .then((data) => {
            this.supremeCourseList = data.data;
            console.log(data);
          })
          .catch((error) => {
            console.error(error);
          })
      },
    }
  }
</script>

<style scoped>
  @import "../common/courseindex.css";
</style>
