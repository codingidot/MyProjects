<template>
<div class="videoDetail">
    <span class="Lalign"><li class="goList" @click="listGo"><a href="#">List</a></li></span>
    <h1 class="title">{{ title }}</h1>
    <br><br><hr class="line"><br><br>
    <video id="testVideo" src="" width="800" height="500" controls></video>
    <br><br><hr class="line"><br><br>
    <GoogleChart :contentsId="contents_id" ref="GoogleChart" class="chart"/>
    <br><br>
    <button class="custom-btn btn-3" @click="agreeGo"><span>인정</span></button>   <button class="custom-btn btn-5" @click="disagreeGo"><span>노인정</span></button>
    <br><br><hr class="line"><br><br>
    무게 : {{ Weight }} kg
    <br><br><hr class="line"><br><br>
    <div class="input-div">
      <textarea id="contents" v-model="contents" class="textarea" disabled></textarea>
    </div>
    <br><br><hr class="line"><br><br>
    <Comment :CONTENTS_ID="CONTENTS_ID"/>
</div>
</template>

<script>
import axios from 'axios'
import Comment from './Comment.vue'
import GoogleChart from './GoogleChart.vue'
export default {
  name: 'videoDetail',
  data () {
    return {
      agree: 0,
      disagree: 0,
      bsd: this.$route.query.BSD,
      cnt: 0,
      contents: '',
      contents_id: this.$route.query.CONTENTS_ID,
      id: '',
      result: '',
      title: '',
      upload_date: '',
      Weight: 0,
      attachment: '',
      ID: this.$store.getters.getID,
      Vpossible: true,
      Vcount: 0,
      finishVoteCount: 3,
      Y: 0,
      N: 0,
      Y_N: ''
    }
  },
  // Creation
  beforeCreate () {
  },
  created () {
  },
  // Mounting
  beforeMount () {
  },
  mounted () {
    this.getDetail() // 콘텐츠 id로 댓글 불러옴
  },
  // Updating
  beforeUpdate () {
  },
  updated () {
  },
  state: {
  },
  props: {

  },
  watch: {

  },
  components: {
    Comment: Comment,
    GoogleChart: GoogleChart
  },
  methods: {
    async getDetail () {
      // 콘텐츠 id로 댓글 불러옴
      await axios
        .get('http://localhost:777/bsd/selectDetail', {
          params: { CONTENTS_ID: this.$route.query.CONTENTS_ID }
        })
        .then((res) => {
          this.agree = res.data.Vdetail[0].AGREE
          this.disagree = res.data.Vdetail[0].DISAGREE
          this.bsd = res.data.Vdetail[0].BSD
          this.cnt = res.data.Vdetail[0].CNT
          this.contents = res.data.Vdetail[0].CONTENTS
          this.contents_id = res.data.Vdetail[0].CONTENTS_ID
          this.id = res.data.Vdetail[0].ID
          this.result = res.data.Vdetail[0].RESULT
          this.title = res.data.Vdetail[0].TITLE
          this.upload_date = res.data.Vdetail[0].UPLOAD_DATE
          this.Weight = res.data.Vdetail[0].WEIGHT
          this.attachment = '/media/' + res.data.Vdetail[0].ATTACHMENT // 스프링부트 서버 기준으로 경로 설정해야함
        })
        .catch((error) => {
          console.log(error)
        })
        .finally(() => {
        })

      var att = this.attachment
      var video = document.querySelector('#testVideo')

      if (this.$route.query.isNew === 'Y') {
        setTimeout(function () {
          // 새롭게 생성된거면 파일 업로드 시간이 필요하니 3초의 시간을 줌
          video.setAttribute('src', att)
          video.load()
        }, 3000)
      } else {
        // 바로 load
        video.setAttribute('src', att)
        video.load()
      }
    },
    async agreeGo () {
      var ask = confirm('투표하면 취소 및 변경이 불가능합니다. 투표를 하시겠습니까?')
      if (!ask) {
        return false
      }
      await this.checkAgree() // 투표 했는지 확인

      if (this.Vcount >= this.finishVoteCount) {
        alert('투표가 완료된 컨텐츠입니다.')
        return false
      }
      if (!this.Vpossible) {
        alert('이미 투표를 하였습니다.')
        return false
      }

      // 투표완료 처리
      if (this.Vcount === (this.finishVoteCount - 1)) {
        // 현재 날짜 시간 가져오기
        var today = new Date()

        var year = today.getFullYear()
        var month = ('0' + (today.getMonth() + 1)).slice(-2)
        var day = ('0' + today.getDate()).slice(-2)
        var hours = String(today.getHours()).padStart(2, '0')
        var minutes = String(today.getMinutes()).padStart(2, '0')
        var seconds = String(today.getSeconds()).padStart(2, '0')
        var milliSeconds = today.getMilliseconds()
        var dateString = year + '-' + month + '-' + day + ' '
        var timeString = hours + ':' + minutes + ':' + seconds + ':' + milliSeconds

        if (this.Y + 1 >= this.N) {
          this.Y_N = 'Y'
        } else {
          this.Y_N = 'N'
        }
        await axios({
          method: 'post',
          url: 'http://localhost:777/bsd/finishVote',
          data: {
            CONTENTS_ID: this.$route.query.CONTENTS_ID,
            ID: this.id,
            BSD: this.bsd,
            WEIGHT: this.Weight,
            DATE: dateString + timeString,
            Y_N: this.Y_N
          }
        })
      }
      // 인정
      await axios({
        method: 'post',
        url: 'http://localhost:777/bsd/insertAgree',
        data: {
          CONTENTS_ID: this.$route.query.CONTENTS_ID,
          ID: this.ID,
          AGREE: 'Y'
        }
      })

      // ref를 통해서 어떤 자식 컴포넌트의 함수를 호출할지 지정할 수 있다.
      this.$refs.GoogleChart.getVoteResult()
    },
    async disagreeGo () {
      var ask = confirm('투표하면 취소 및 변경이 불가능합니다. 투표를 하시겠습니까?')
      if (!ask) {
        return false
      }
      await this.checkAgree() // 투표 했는지 확인

      if (this.Vcount >= this.finishVoteCount) {
        alert('투표가 완료된 컨텐츠입니다.')
        return false
      }
      if (!this.Vpossible) {
        alert('이미 투표를 하였습니다.')
        return false
      }

      // 투표완료 처리
      if (this.Vcount === (this.finishVoteCount - 1)) {
        // 현재 날짜 시간 가져오기
        var today = new Date()

        var year = today.getFullYear()
        var month = ('0' + (today.getMonth() + 1)).slice(-2)
        var day = ('0' + today.getDate()).slice(-2)
        var hours = String(today.getHours()).padStart(2, '0')
        var minutes = String(today.getMinutes()).padStart(2, '0')
        var seconds = String(today.getSeconds()).padStart(2, '0')
        var milliSeconds = today.getMilliseconds()
        var dateString = year + '-' + month + '-' + day + ' '
        var timeString = hours + ':' + minutes + ':' + seconds + ':' + milliSeconds

        if (this.Y >= this.N + 1) {
          this.Y_N = 'Y'
        } else {
          this.Y_N = 'N'
        }
        await axios({
          method: 'post',
          url: 'http://localhost:80/bsd/finishVote',
          data: {
            CONTENTS_ID: this.$route.query.CONTENTS_ID,
            ID: this.id,
            BSD: this.bsd,
            WEIGHT: this.Weight,
            DATE: dateString + timeString,
            Y_N: this.Y_N
          }
        })
      }
      // 노인정
      await axios({
        method: 'post',
        url: 'http://localhost:777/bsd/insertAgree',
        data: {
          CONTENTS_ID: this.contents_id,
          ID: this.ID,
          AGREE: 'N'
        }
      })

      // ref를 통해서 어떤 자식 컴포넌트의 함수를 호출할지 지정할 수 있다.
      this.$refs.GoogleChart.getVoteResult()
    },
    async checkAgree () {
      var result = ''
      var result2 = 0
      // 해당 컨텐츠 투표했는지 확인
      await axios
        .get('http://localhost:777/bsd/checkAgree', {
          params: { CONTENTS_ID: this.$route.query.CONTENTS_ID, ID: this.ID }
        })
        .then((res) => {
          result = res.data.Vlist[0].VOTE
          result2 = res.data.Vlist[0].COUNTNUM
          this.Y = res.data.Vlist[0].AGREE
          this.N = res.data.Vlist[0].DISAGREE
        })
        .catch((error) => {
          console.log(error)
        })
        .finally(() => {
        })

      if (result === 'CANT') {
        this.Vpossible = false
      } else {
        this.Vpossible = true
      }

      this.Vcount = result2
    },
    listGo () {
      this.$router.push({ name: 'Vlist', query: { BSD: this.bsd } })
    }
  }
}
</script>

<style>
#testVideo {
  outline: solid gray 5px;
}

.videoDetail {
  background: #2b2b2b;
  line-height: 22px;
  padding: 40px;
  width: 100%;
  height: 100%;
  min-height: 672px;
  color: white;
}

.title {
  color: rgb(233, 231, 92);
  text-align: center;
}

.chart {
  align-items: center;
}

.line {
    background-color: rgb(235, 54, 54);
}

button {
  margin: 20px;
}
.custom-btn {
  width: 130px;
  height: 40px;
  color: #fff;
  border-radius: 5px;
  padding: 10px 25px;
  font-family: 'Lato', sans-serif;
  font-weight: 500;
  background: transparent;
  cursor: pointer;
  transition: all 0.3s ease;
  position: relative;
  display: inline-block;
   box-shadow:inset 2px 2px 2px 0px rgba(255,255,255,.5),
   7px 7px 20px 0px rgba(0,0,0,.1),
   4px 4px 5px 0px rgba(0,0,0,.1);
  outline: none;
}

/* 3 */
.btn-3 {
  background: rgb(0,172,238);
background: linear-gradient(0deg, rgba(0,172,238,1) 0%, rgba(2,126,251,1) 100%);
  width: 130px;
  height: 40px;
  line-height: 42px;
  padding: 0;
  border: none;

}
.btn-3 span {
  position: relative;
  display: block;
  width: 100%;
  height: 100%;
}
.btn-3:before,
.btn-3:after {
  position: absolute;
  content: "";
  right: 0;
  top: 0;
   background: rgba(2,126,251,1);
  transition: all 0.3s ease;
}
.btn-3:before {
  height: 0%;
  width: 2px;
}
.btn-3:after {
  width: 0%;
  height: 2px;
}
.btn-3:hover{
   background: transparent;
  box-shadow: none;
}
.btn-3:hover:before {
  height: 100%;
}
.btn-3:hover:after {
  width: 100%;
}
.btn-3 span:hover{
   color: rgba(2,126,251,1);
}
.btn-3 span:before,
.btn-3 span:after {
  position: absolute;
  content: "";
  left: 0;
  bottom: 0;
   background: rgba(2,126,251,1);
  transition: all 0.3s ease;
}
.btn-3 span:before {
  width: 2px;
  height: 0%;
}
.btn-3 span:after {
  width: 0%;
  height: 2px;
}
.btn-3 span:hover:before {
  height: 100%;
}
.btn-3 span:hover:after {
  width: 100%;
}

/* 5 */
.btn-5 {
  width: 130px;
  height: 40px;
  line-height: 42px;
  padding: 0;
  border: none;
  background: rgb(255,27,0);
background: linear-gradient(0deg, rgba(255,27,0,1) 0%, rgba(251,75,2,1) 100%);
}
.btn-5:hover {
  color: #f0094a;
  background: transparent;
   box-shadow:none;
}
.btn-5:before,
.btn-5:after{
  content:'';
  position:absolute;
  top:0;
  right:0;
  height:2px;
  width:0;
  background: #f0094a;
  box-shadow:
   -1px -1px 5px 0px #fff,
   7px 7px 20px 0px #0003,
   4px 4px 5px 0px #0002;
  transition:400ms ease all;
}
.btn-5:after{
  right:inherit;
  top:inherit;
  left:0;
  bottom:0;
}
.btn-5:hover:before,
.btn-5:hover:after{
  width:100%;
  transition:800ms ease all;
}
.goList {
  display: inline-block;
  vertical-align: middle;
  text-align: center;
  margin: .25rem;
  padding: .5rem 1rem;
  text-decoration: none;
  font-weight: bold;
  color: white;
  background: teal;
}

.goList:hover {
  background: yellowgreen;
  background-color: yellow;
}

.Lalign {
  float: left;
}

.input-div{
    vertical-align: middle;
 }
</style>
