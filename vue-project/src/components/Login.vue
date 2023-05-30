<template>
  <div class="wrap">
    <div class="home1" style="position: absolute; width :100%; top : 10%; ">
      <div class="home" style="position: relative;">
        <img alt="background" src="../assets/bg.jpg" style="width : 100%; top: 30px;">
        <!-- <HelloWorld msg="Prove your ability"/> -->
        <div  class="cover" style="position : absolute; width :100%; top : 100px ;">

            <div class="login">
                    <h2>Log-in</h2>
                    <br>
                    <br>
                    <br>
                    <div class="login_id">
                        <h4>E-mail</h4>
                        <input type="email" name="" id="" placeholder="Email" v-model="id">
                    </div>
                    <div class="login_pw">
                        <h4>Password</h4>
                        <input type="password" name="" id="" placeholder="Password" v-model="pw">
                    </div>
                    <div class="login_etc">
                        <div class="sign_in">
                          <router-link to="/login/sign-in">Sign-in</router-link>
                        </div>
                        <div class="forgot_pw">
                          <router-link to="/login/find">Forgot password?</router-link>
                        </div>
                    </div>
                    <div id = "result" v-show="is_show" style="color :red">
                        <h5>아이디 또는 비밀번호가 일치하지 않습니다.</h5>
                        <button class="okButton" @click="handle_toggle" type="button" >
                        확인
                        </button>
                    </div>
                    <div class="submit">
                        <input @click="check()" type="button" value="submit">
                    </div>
                    <br>
                    <br>
                </div>
          </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'LoginForm',
  data () {
    return {
      id: '',
      pw: '',
      is_show: false
    }
  },
  mounted () {

  },
  props: {

  },
  watch: {

  },
  methods: {
    check () {
      axios
        .get('http://localhost:777/bsd/check', {
          params: { id: this.id, pw: this.pw }
        })
        .then((res) => {
          if (res.data.result.length === 0) {
            this.is_show = true
          } else {
            this.$store.commit('loginSuccess', this.id)
            // localStorage.setItem('login', '성공')
            this.loginState = 'Log-out'
            this.handle_toggle()
            this.$router.push('/').catch(() => {})
          }
        })
        .catch((error) => {
          console.log(error)
        })
        .finally(() => {
        })
    },
    handle_toggle () {
      this.is_show = false
    }
  }
}

</script>
<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: "Noto Sans KR", sans-serif;
}

a {
  text-decoration: none;
  color: white;
}

li {
  list-style: none;
}

.cover {
  display: flex;
  align-items: center;
  justify-content: center;
}

.wrap {
  width: 100%;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: rgba(0, 0, 0, 0.1);
}

.login {
  width: 30%;
  height: 600px;
  background: gray;
  border-radius: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}

h2 {
  color: white;
  font-size: 2em;
}
.login_sns {
  padding: 20px;
  display: flex;
}

.login_id {
  margin-top: 20px;
  width: 80%;
}

.login_id input {
  width: 100%;
  height: 50px;
  border-radius: 30px;
  margin-top: 10px;
  padding: 0px 20px;
  border: 1px solid lightgray;
  outline: none;
}

.login_pw {
  margin-top: 20px;
  width: 80%;
}

.login_pw input {
  width: 100%;
  height: 50px;
  border-radius: 30px;
  margin-top: 10px;
  padding: 0px 20px;
  border: 1px solid lightgray;
  outline: none;
}

.login_etc {
  padding: 10px;
  width: 80%;
  font-size: 14px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-weight: bold;
  color: white;
}

.submit {
  margin-top: 50px;
  width: 80%;
}

.submit input {
  width: 100%;
  height: 50px;
  border: 0;
  outline: none;
  border-radius: 40px;
  background: black;
  color: white;
  font-size: 1.2em;
  letter-spacing: 2px;
}

.okButton {
  border-radius: 40px;
  background: black;
  color: white;
  letter-spacing: 2px;
}
</style>
