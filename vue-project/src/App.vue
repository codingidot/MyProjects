<template>
  <nav>
    <router-link @click="checkSession" to= '/login'>{{loginState}}</router-link> |
    <router-link to="/">Home</router-link>
  </nav>
  <router-view/>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;

}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
<script>
export default {
  data () {
    return {
      loginState: 'Log-in'
    }
  },
  mounted () {

  },
  watch: {
    // nav 이동 감지
    '$route' (to, from) {
      if (this.$store.getters.getID !== null) { // 로그인 성공
        this.loginState = 'Log-out'
      } else { // 로그인 안한경우
        this.loginState = 'Log-in'
      }

      // logout 클릭시 locatstorage 삭제
      if (from.path === '/' && to.path === '/login' && this.loginState === 'Log-out') {
        this.loginState = 'Log-in'
        this.$store.commit('loginSuccess', null)
      }
      if (from.path === '/' && to.path === '/login' && this.$store.getters.getID !== null) {
        this.loginState = 'Log-in'
      }
    }
  },
  methods: {

  }
}
</script>
