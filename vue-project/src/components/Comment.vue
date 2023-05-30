<template>
<div id="real_comment" class="real_comment">
  <h3>Leave your comment!</h3><br><br>
  <input type="text" v-model="commentValue" class="insertComment" ><button class="enter" @click="goComment()">댓글 등록</button>
    <table id="Ctable" class="tableC">
      <thead class="Chead">
      <tr>
          <th style="width:15%">닉네임</th>
          <th style="width:50%">내용</th>
          <th style="width:10%">좋아요</th>
          <th style="width:10%">싫어요</th>
          <th style="width:15%">날짜</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(item, idx) in Clist" v-bind:key="idx" class="Crow">
          <!-- <td v-show="contents_id">{{item.CONTENTS_ID}}</td>
          <td v-show="bsd">{{item.BSD}}</td> -->
          <td class="cell" @click="moreComment(item)">{{item.NICKNAME}}</td>
          <td class="cell" @click="moreComment(item)">{{item.TALK}}</td>
          <td class="cell" ><img src="/img/thumsUp.png" style="width: 15px; height: 15px;" @click="addLike(item)"/>&nbsp;{{item.CL_LIKE}}</td>
          <td class="cell" ><img src="/img/thumsDown.png" style="width: 15px; height: 15px;" @click="addDislike(item)"/>&nbsp;{{item.CL_DISLIKE}}</td>
          <td class="cell" @click="moreComment(item)">{{item.CL_DATE + ' - ' +item.CL_TIME.substring(0,5)}}</td>
      </tr>
      </tbody>
  </table>

</div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'comment_Component',
  data () {
    return {
      Clist: [],
      date: '',
      CONTENTS_ID: '',
      commentValue: ''
    }
  },
  mounted () {
    this.getCommtent()
    this.CONTENTS_ID = this.$route.query.CONTENTS_ID
  },
  props: {

  },
  watch: {

  },
  methods: {
    getCommtent () {
      axios
        .get('http://localhost:777/bsd/selectComment', {
          params: { CONTENTS_ID: this.$route.query.CONTENTS_ID }
        })
        .then((res) => {
          this.Clist = res.data.Clist
        })
        .catch((error) => {
          console.log(error)
        })
        .finally(() => {
        })
    },
    moreComment () {
    },
    async goComment () {
      // 현재 날짜 시간 가져오기
      var today = new Date()

      var year = today.getFullYear()
      var month = ('0' + (today.getMonth() + 1)).slice(-2)
      var day = ('0' + today.getDate()).slice(-2)
      var hours = String(today.getHours()).padStart(2, '0')
      var minutes = String(today.getMinutes()).padStart(2, '0')
      var seconds = String(today.getSeconds()).padStart(2, '0')
      var milliSeconds = today.getMilliseconds()
      var dateString = year + '-' + month + '-' + day
      var timeString = hours + ':' + minutes + ':' + seconds + ':' + milliSeconds

      // 댓글이 빈 문자열이면 등록 안함
      if (this.commentValue === '') {
        return false
      }

      // 전달될 파라미터
      var data = { TALK: this.commentValue, CONTENTS_ID: this.CONTENTS_ID, CLEVEL: 1, DATE: dateString, ID: this.$store.getters.getID, TIME: timeString }

      // 댓글 전송시 초기화
      this.commentValue = ''

      // 댓글 등록
      await axios({
        method: 'post', // [요청 타입]
        url: 'http://localhost:777/bsd/insertComment', // [요청 주소]
        data: JSON.stringify(data), // [요청 데이터]
        headers: {
          'Content-Type': 'application/json; charset=utf-8'
        }, // [요청 헤더]
        timeout: 5000 // [타임 아웃 시간]
        // responseType: "json" // [응답 데이터 : stream , json]
      })
        .then(function (response) {
          // then은 response 결과를 전역변수나 data에 담을 수 없다.
        })
        .catch(function (error) {
          console.log('ERROR : ' + JSON.stringify(error))
        })

      this.getCommtent()
    },
    addLike (item) {
      // 좋아요 +1
      axios
        .get('http://localhost:777/bsd/updateLike', {
          params: { CONTENTS_ID: this.$route.query.CONTENTS_ID, ID: item.ID, CLEVEL: item.CLEVEL, CL_DATE: item.CL_DATE, CL_TIME: item.CL_TIME, CLICK_ID: this.$store.getters.getID }
        })
        .then((res) => {
          this.getCommtent() // 댓글 조회
        })
        .catch((error) => {
          console.log(error)
        })
        .finally(() => {
        })
    },
    addDislike (item) {
      // 싫어요 +1
      axios
        .get('http://localhost:777/bsd/updateDisLike', {
          params: { CONTENTS_ID: this.$route.query.CONTENTS_ID, ID: item.ID, CLEVEL: item.CLEVEL, CL_DATE: item.CL_DATE, CL_TIME: item.CL_TIME, CLICK_ID: this.$store.getters.getID }
        })
        .then((res) => {
          this.getCommtent() // 댓글 조회
        })
        .catch((error) => {
          console.log(error)
        })
        .finally(() => {
        })
    }
  }
}
</script>

<style>
.real_comment {
    background-color: gray;
    color: cornsilk;
}

.tableC td {
    overflow: overlay;
    max-width: 900px;
    display: table-cell;
    padding: 15px;
    border-bottom: 1px solid ;
}
.tableC {
    width: 100%;
}

.Ctable {
    max-width: 500px;
}
.insertComment {
    width: 30%;
    height: 50px;
    border: 0;
    outline: none;
    border-radius: 40px;
    background: black;
    color: white;
    font-size: 1.2em;
    letter-spacing: 2px;
    text-align: center;
}
.enter {
    width: 5%;
    border-radius: 40px;
    background: blueviolet;
    color: white;
    letter-spacing: 2px;
}
</style>
