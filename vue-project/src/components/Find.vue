<template>
  <div class="wrap">
    <div class="home1" style="position: absolute; width :100%; top : 10%; ">
      <div class="home" style="position: relative;">
        <img alt="background" src="../assets/bg.jpg" style="width : 100%; height : 100%;top: 30px;">
        <!-- <HelloWorld msg="Prove your ability"/> -->
        <div  class="cover" style="position : absolute; width :100%; top : 100px ;">

            <div class="login">
                    <h2>Find Password</h2>
                    <div class="login_id">
                        <h4>E-mail</h4>
                        <input type="email" name="" id="" placeholder="Email" v-model="id">
                        <button class="verifyButton" @click="send()">verify</button>
                        <input type="email" name="" id="" placeholder="Number from Email" v-model="wirttenNum">
                        <button class="checkButton" @click="checkNum()">check</button>
                    </div>
                    <div class="login_pw">
                        <h4>New Password</h4>
                        <input type="password" @keyup="checkPw()" name="" id="" placeholder="Password" v-model="pw">
                        <div id = "result" v-show="is_show" style="color :red">
                        <h5>5자리 이상 입력해주세요.</h5>
                        </div>
                        <input type="password" @keyup="checkPw2()" name="" id="" placeholder="Password Again" v-model="pw2">
                        <div id = "result" v-show="is_show2" style="color :red">
                        <h5>비밀번호가 일치하지 않습니다.</h5>
                        </div>
                    </div>
                    <div class="submit">
                        <input @click="updatePW()" type="button" value="Sign - in">
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
export default ({
  name: 'FindForm',
  data () {
    return {
      id: '',
      pw: '',
      pw2: '',
      vefifyNum: '',
      wirttenNum: '',
      verifySuccess: '',
      is_show: false,
      is_show2: false
    }
  },
  mounted () {

  },
  props: {

  },
  watch: {

  },
  methods: {
    async send () {
      var idCheck = false

      if (this.id === '') {
        alert('이메일을 입력해주세요.')
        return false
      }

      await axios
        .get('http://localhost:777/bsd/checkID', {
          params: { id: this.id }
        })
        .then((res) => {
          if (res.data.result.length > 0) {
            idCheck = true
          }
        })
        .catch((error) => {
          console.log(error)
        })
        .finally(() => {

        })

      if (!idCheck) {
        alert('회원가입되지 않은 이메일 주소입니다.')
        return false
      }

      axios
        .get('http://localhost:777/bsd/verify', {
          params: { id: this.id }
        })
        .then((res) => {
          if (res.data.result === 'NO') {
            alert('잘못된 이메일 주소입니다.')
          } else {
            this.vefifyNum = res.data.result
            alert('발송되었습니다.')
          }
        })
        .catch((error) => {
          console.log(error)
        })
        .finally(() => {

        })
    },
    checkNum () {
      if (this.vefifyNum === this.wirttenNum) {
        alert('인증 완료되었습니다.')
        this.verifySuccess = 'Y'
      } else {
        alert('잘못된 인증번호 입니다.')
      }
    },
    updatePW () {
      var pass = true
      var msg = ''
      if (this.verifySuccess !== 'Y') {
        alert('이메일 인증을 해주세요.')
        return false
      }

      var arr = [this.id, this.pw, this.pw2]

      arr.forEach((element, index) => {
        if (!element || element === '') {
          if (index === 0) {
            msg += ' 이메일 '
            pass = false
          } else if (index === 1) {
            msg += ' 비밀번호 '
            pass = false
          } else if (index === 2) {
            msg += ' 비밀번호 확인 '
            pass = false
          }
        }
      })

      if (!pass) {
        alert(msg + '입력해주세요.')
        return false
      }

      if (this.pw !== this.pw2) {
        alert('비밀번호가 일치하지 않습니다.')
        return false
      }
      axios
        .get('http://localhost:777/bsd/updatePW', {
          params: { id: this.id, pw: this.pw }
        })
        .then((res) => {
          alert('비밀번호가 변경되었습니다.')
          this.$router.push('/login').catch(() => {})
        })
        .catch((error) => {
          console.log(error)
        })
        .finally(() => {

        })
    },
    checkPw () {
      if (this.verifySuccess !== 'Y') {
        alert('이메일 인증 후 새로운 비밀번호를 입력해주세요.')
        this.pw = ''
        return false
      }

      if (this.pw.length < 5) {
        this.is_show = true
      } else {
        this.is_show = false
      }
    },
    checkPw2 () {
      if (this.pw !== this.pw2) {
        this.is_show2 = true
      } else {
        this.is_show2 = false
        this.pw_same = true
      }
    }
  }
})
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
  height: 80%;
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

.login_userInfo {
  margin-top: 20px;
  width: 80%;
}

.login_userInfo input {
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

.verifyButton {
  border-radius: 40px;
  background: black;
  color: white;
  letter-spacing: 2px;
  width: 30%;
}

.checkButton{
  border-radius: 40px;
  background: black;
  color: white;
  letter-spacing: 2px;
  width: 30%;
}
</style>
