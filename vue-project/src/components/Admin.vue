<template>
<div>

    <div class="home" style="position: relative;">
        <img alt="background" src="../assets/bg.jpg" style="width : 100%; height: 100%; min-height: 672px;">
        <!-- <HelloWorld msg="Prove your ability"/> -->
        <div  style="position : absolute; width :100%; top : 10% ;">
        <h1 style="text-align= center; left = 50%; width : 100%"><b>관리자 페이지</b></h1><br>
        <button class="w-btn w-btn-indigo" style="width : 20% " @click="move('video')">게시물 관리</button>
        <button class="w-btn w-btn-indigo" style="width : 20% " @click="move('member')">회원 관리</button>
        </div>

    </div>

</div>
</template>
<script>
// @ is an alias to /src
// import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'AdminView',
  data () {
    return {
    }
  },
  mounted () {
  },
  methods: {

    move (item) {
      if (item === 'video') { // 동영상 목록
        this.$router.push({ name: 'AdminVideoView' })
      } else if (item === 'member') { // 회원 목록
        this.$router.push({ name: 'AdminMemberView' })
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
