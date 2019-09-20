import axios from 'axios'
import qs from 'qs'
import vue from 'vue'
const request = (url, body, type = 'get', isJson = false, baseUrlRewrite) => {
  const query = {
    url:   url,
    method: type,
    withCredentials: true,
    timeout: 30000
  }
  if (type === 'get') {
    query.params = body
  } else {
    query.data = isJson ? body : qs.stringify(body)
  }
  return axios.request(query)
    .then(res => {
      return new Promise((resolve, reject) => {
        if (!res.data) {
          reject(new Error('服务器响应超时'))
          return
        }
        resolve(res.data);
      })
    }, e => {
      switch (e.response.status) {
        case 401: // 未登录跳转至登录页
          if (cookies.getCookie('ucarincLogoutUrl')) {
            const ucarincLogoutUrl = cookies.getCookie('ucarincLogoutUrl')
            location.href = ucarincLogoutUrl
            cookies.delCookie('ucarincLogoutUrl')
          } else {
            top.window.postMessage({
              type: 'NO_LOGIN',
              msg: '401'
            }, '*')
            top.location.href = getDynamicUrl('http://contacts.ucarinc.com');
          }
          return Promise.reject(new Error('未登录，请重新登录'))
        case 403: // 无权限操作
          // top.window.postMessage({
          //   type: 'NO_PERMISSION',
          //   msg: '403'
          // }, '*')
          return Promise.reject(new Error('对不起，您暂无操作权限'))
        default:
          break
      }
      return Promise.reject(e.response)
    })
    .catch(e => {
      console.log(e.status)
      switch (e.status) {
        case 401: // 未登录跳转至登录页
          top.location.href = e.re.loginUrl + "?service="+getDynamicUrl('http://contacts.ucarinc.com');
        default:
          break
      }
      return Promise.reject(e.response)
      vue.prototype.$message.error(e && e.message ? e.message : '系统错误')
      return Promise.reject(e)
    })
}
axios.interceptors.request.use(config => {
  config.headers['Request-Ajax'] = 'true';
  return config
}, error => {
  return Promise.reject(error)
})
const requestGetUrl = (url, body) => {
  let link = '?'
  if (url.indexOf('?') !== -1) {
    link = '&'
  }
  let params = ''
  if (body) {
    params = link + qs.stringify(body)
  }
  return getBaseUrl() + url + params
}

export default {
}

export const categoryApi = {
  getCategoryTree: (query) => request('/content/category/tree', query, 'get'),
  getCategoryData: (query) => request('/content/category/catedata', query, 'get'),
}

export const courseApi = {
  getLatestCourseList: (query) => request('/content/latestlist', query, 'get'),
  getSupremeCourseList: (query) => request('/content/supremelist', query, 'get'),
  getCourseListInCate: (query) => request('/content/list', query, 'get'),
  getAllCourseListInTopLevelCate: (query) => request('/content/alllist', query, 'get'),
  getCourseDetail: (query) => request('/content/' + query.id, null, 'get'),
}

export const teacherApi = {
  getTeacherInfo: (query) => request('/teacher/' + query.id, null, 'get'),
}
