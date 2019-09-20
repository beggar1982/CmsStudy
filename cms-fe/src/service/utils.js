export const recursion = (array, key, value, childKey) => {
  for (let i = 0; i < array.length; i++) {
    if (array[i][key] === value) return array[i]
    if (array[i][childKey] && array[i][childKey] instanceof Array && array[i][childKey].length > 0) {
      let result = recursion(array[i][childKey], key, value, childKey)
      if (result) return result
    }
  }
  return null
}

export const parseRoute = (menus) => {
  const mixin = (obj) => {
    const base = {
      data () {
        return {
          $ucarConfig: {
            autoClose: false,
            openerCallBack: ''
          }
        }
      },
      computed: {
        userPermissions () {
          return this.$store.getters.permissions
        }
      },
      methods: {
        registryVm () {
          window.$currentVm = this
        },
        pageOver () {
          const timer = arguments[arguments.length - 1] &&
          typeof arguments[arguments.length - 1] === 'number' ? arguments[arguments.length - 1] : 300
          const opener = window.opener
          if (!opener) return
          const {autoClose, openerCallBack} = this.$data.$ucarConfig
          if (openerCallBack &&
            typeof opener.$currentVm[openerCallBack] === 'function') {
            opener.$currentVm[openerCallBack](...arguments)
          }
          if (autoClose === true) {
            setTimeout(() => {
              window.close()
            }, timer)
          }
        },
        callNewPage (url, options = {}) {
          if (!options.autoClose) options.autoClose = true
          let path = /\/$/.test(url) ? url : `${url}/`
          const target = /^(http|https):\/\//.test(path) ? url
            : `/#${url}`
          let queryStr = ''
          for (let key in options) {
            queryStr += queryStr === '' ? `?${key}=${options[key]}` : `&${key}=${options[key]}`
          }
          window.open(`${target}${queryStr}`)
        }
      },
      mounted () {
        this.$data.$ucarConfig.autoClose = !!this.$route.query.autoClose
        this.$data.$ucarConfig.openerCallBack = this.$route.query.callback
        this.registryVm()
      },
      beforeRouteUpdate () {
        this.registryVm()
      }
    }
    if (obj.mixins) {
      obj.mixins.push(base)
    } else {
      obj.mixins = [base]
    }
    return obj
  }
  const routes = []
  const traverse = (list) => {
    for (let i = 0; i < list.length; i++) {
      const url = list[i].menuUrl || list[i].path
      if (url && !/^_/.test(url)) {
        const src = url.replace(/.+#/, '').replace(/\/:[\w_\d]+/g, '')
        const path = url.replace(/.+#/, '')
        if (require(`@/pages${src}`).default) {
          routes.push({
            path,
            component: mixin(require(`@/pages${src}`).default)
          })
        } else {
          console.warn('未能解析的文件路径:', src)
        }
      }
      const children = list[i].children || list[i].subMenus
      if (children instanceof Array && children.length > 0) {
        traverse(children)
      }
    }
    return routes
  }
  return traverse(menus)
}

export const cookies = {
  // 设置cookie
  setCookie: (cName, value, expiredays) => {
    var exdate = new Date()
    exdate.setDate(exdate.getDate() + expiredays)
    document.cookie = cName + '=' + escape(value) + ((expiredays === null) ? '' : ';expires=' + exdate.toGMTString())
  },
// 获取cookie
  getCookie: (name) => {
    let reg = new RegExp('(^| )' + name + '=([^;]*)(;|$)')
    let arr = document.cookie.match(reg)
    if (arr) {
      return unescape(arr[2])
    } else {
      return null
    }
  },
// 删除cookie
  delCookie: (name) => {
    var exp = new Date()
    exp.setTime(exp.getTime() - 1)
    var cval = cookies.getCookie(name)
    if (cval !== null) {
      document.cookie = name + '=' + cval + ';expires=' + exp.toGMTString()
    }
  }
}

export const mixin = (obj) => {
  const base = {
    data () {
      return {
        $ucarConfig: {
          autoClose: false,
          openerCallBack: ''
        }
      }
    },
    computed: {
      userPermissions () {
        return this.$store.getters.permissions
      }
    },
    methods: {
      registryVm () {
        window.$currentVm = this
      },
      pageOver () {
        const timer = arguments[arguments.length - 1] &&
        typeof arguments[arguments.length - 1] === 'number' ? arguments[arguments.length - 1] : 300
        const opener = window.opener
        if (!opener) return
        const {autoClose, openerCallBack} = this.$data.$ucarConfig
        if (openerCallBack &&
          typeof opener.$currentVm[openerCallBack] === 'function') {
          opener.$currentVm[openerCallBack](...arguments)
        }
        if (autoClose === true) {
          setTimeout(() => {
            window.close()
          }, timer)
        }
      },
      callNewPage (url, options = {}) {
        if (!options.autoClose) options.autoClose = true
        let path = /\/$/.test(url) ? url : `${url}/`
        const target = /^(http|https):\/\//.test(path) ? url
          : `/#${url}`
        let queryStr = ''
        for (let key in options) {
          queryStr += queryStr === '' ? `?${key}=${options[key]}` : `&${key}=${options[key]}`
        }
        window.open(`${target}${queryStr}`)
      }
    },
    mounted () {
      this.$data.$ucarConfig.autoClose = !!this.$route.query.autoClose
      this.$data.$ucarConfig.openerCallBack = this.$route.query.callback
      this.registryVm()
    },
    beforeRouteUpdate () {
      this.registryVm()
    }
  }
  if (obj.mixins) {
    obj.mixins.push(base)
  } else {
    obj.mixins = [base]
  }
  return obj
}
