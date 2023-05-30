<template>
  <div>

    <div class="home" style="position: relative;">
        <img alt="background" src="../assets/bg.jpg" style="width : 100%; height: 100%; min-height: 672px;">
        <!-- <HelloWorld msg="Prove your ability"/> -->
        <div  style="position : absolute; width :100%; top : 10% ;">
          <h1 style="text-align= center; left = 50%; width : 100%"><b>BSD - Prove your ability</b></h1><br>
          <button class="w-btn w-btn-indigo" style="width : 20% " @click="move('B')">B : Bench Press</button><br>
          <button class="w-btn w-btn-indigo" style="width : 20% " @click="move('S')">S : Squat</button><br>
          <button class="w-btn w-btn-indigo" style="width : 20% " @click="move('D')">D : Deadlift</button><br>
          <button class="w-btn w-btn-indigo" style="width : 20% " @click="move('Rank')">Ranking</button><br>
          <button class="w-btn w-btn-indigo" style="width : 20% " @click="move('mypage')">My Page</button><br>
          <button class="w-btn w-btn-indigo" style="width : 20% " @click="move('admin')" v-show="is_show">Admin</button>
        </div>

    </div>

  </div>
</template>
<script>
// @ is an alias to /src
// import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'HomeView',
  data () {
    return {
      loginState: 'Log-in',
      is_show: false
    }
  },
  mounted () {
    this.checkSession()
    // 관리자 메뉴
    if (this.$store.getters.getID === 'admin') {
      this.is_show = true
    }
  },
  methods: {
    checkSession () {
      if (this.$store.getters.getID !== null) {
        this.loginState = 'Logout'
      }
    },
    move (item) {
      if (this.$store.getters.getID === null) {
        alert('로그인 후 입장 가능합니다.')
        return false
      }

      if (item === 'B' || item === 'S' || item === 'D') { // 동영상 리스트
        this.$router.push({ name: 'Vlist', query: { BSD: item } })
      } else if (item === 'Rank') { // 랭킹
        this.$router.push({ name: 'Rlist', query: { ID: this.$store.getters.getID } })
      } else if (item === 'mypage') { // 마이페이지
        this.$router.push({ name: 'MyPage', query: { ID: this.$store.getters.getID } })
      } else if (item === 'admin') { // 관리자페이지
        this.$router.push({ name: 'AdminView', query: { good: true } })
      }
    }
  }
}
</script>
<style>

button {
    margin: 20px;
}

.w-btn {
    position: relative;
    border: none;
    display: inline-block;
    padding: 15px 30px;
    border-radius: 15px;
    font-family: "paybooc-Light", sans-serif;
    box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
    text-decoration: none;
    font-weight: 600;
    transition: 0.25s;
}

.w-btn-outline {
    position: relative;
    padding: 15px 30px;
    border-radius: 15px;
    font-family: "paybooc-Light", sans-serif;
    box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
    text-decoration: none;
    font-weight: 600;
    transition: 0.25s;
}

.w-btn-indigo {
    background-color: aliceblue;
    color: #1e6b7b;
}

.w-btn:hover {
    letter-spacing: 2px;
    transform: scale(1.2);
    cursor: pointer;
}
</style>
