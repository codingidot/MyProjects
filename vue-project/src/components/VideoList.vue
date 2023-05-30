<template>
  <div class="videoList">
    <h1 class="comment">{{ Comment }}</h1>
    <span class="search"><input type="text" class="searchBar" v-model="searchVal"  placeholder="  search" /><li class="createV" @click="searchGo"><a href="#">검색</a></li></span>
    <span class="align"><li class="createV" v-on:click="modalOpen = true"><a href="#">글쓰기</a></li></span>
    <div class="tableH">
      <table id="table" class="table" v-if="table_show">
        <thead class="head">
          <tr>
              <th v-show=false>게시물 ID</th>
              <th v-show=false>종목</th>
              <th style="width:7%">인정여부</th>
              <th style="width:15%">닉네임</th>
              <th style="width:58%">제목</th>
              <th style="width:10%">날짜</th>
              <th style="width:5%">조회수</th>
              <th style="width:5%">무게</th>
          </tr>
        </thead>
        <tbody id="tableBody">
          <tr v-for="(item, idx) in Vlist" v-bind:key="idx" class="row">
              <!-- <td v-show="contents_id">{{item.CONTENTS_ID}}</td>
              <td v-show="bsd">{{item.BSD}}</td> -->
              <td class="cell" @click="goDetail(item)">{{item.CL_RESULT}}</td>
              <td class="cell" @click="goDetail(item)">{{item.NICKNAME}}</td>
              <td class="cell" @click="goDetail(item)">{{item.TITLE}}</td>
              <td class="cell" @click="goDetail(item)">{{item.UPLOAD_DATE}}</td>
              <td class="cell" @click="goDetail(item)">{{item.CNT}}</td>
              <td class="cell" @click="goDetail(item)">{{item.WEIGHT}}</td>
          </tr>
        </tbody>
      </table>
    </div>
    <br>
    <br>
    <br>
    <div class="noData"><h1 v-show="no_show" class="no">데이터가 없습니다.</h1></div>
    <br>
    <br>
    <div class="btn-cover" v-if="paging_show">
    <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">
        이전
    </button>
    <span class="page-count">{{ pageNum + 1 }} / {{ pageCount }} 페이지</span>
    <button :disabled="pageNum >= pageCount - 1" @click="nextPage" class="page-btn">
        다음
    </button>
    </div>
    <!-- 모달창 -->
    <div class="big-bg" v-if="modalOpen === true">
      <div class="small-bg">
        <h3>Prove your ability</h3>
        <div class="write">
          <div>
            <span class="label"><label for="title">제목</label></span>
            <input type="text" id="title" class="input" v-model="title" />
          </div>
          <div>
            <span class="label"><label for="contents">내용</label><br></span>
            <textarea id="contents" v-model="contents" class="textarea"></textarea>
          </div>
          <div>
            <span class="label"><label for="BSD">종목</label></span>
            <span><input type="text" id="BSD" class="input" v-model="BSD" readonly /></span>
          </div>
          <div>
            <span class="label"><label for="weight">무게(kg)</label></span>
            <input type="text" id="weight" class="input" v-model="weight" />
          </div>
          <div id="app">
            <div class="custom-file">
              <input id="customFile" type="file" @change="handleFileChange"/>
              <label class="custom-file-label" for="customFile">{{file_name}}</label>
            </div>
            <button @click="upload" class="modal-btn">
              올리기
            </button>
            <button v-on:click="close" class="modal-btn">
              닫기
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'VideoList',
  data () {
    return {
      modalOpen: false,
      no_show: false,
      table_show: true,
      Vlist: [{}],
      Comment: '',
      BSD: '',
      title: '좋은 평가 부탁드립니다.',
      contents: '',
      weight: '',
      file_name: '',
      file_extend: '',
      file: '',
      date: '',
      contents_id: '',
      pageNum: 0,
      pageSize: 10,
      pageCount: 0,
      searchVal: '',
      paging_show: true
    }
  },
  mounted () {
    this.getList()
    if (this.$route.query.BSD === 'B') {
      this.Comment = 'B : Bench press'
      this.BSD = 'Bench press'
    } else if (this.$route.query.BSD === 'S') {
      this.Comment = 'S : Squat'
      this.BSD = 'Squat'
    } else if (this.$route.query.BSD === 'D') {
      this.Comment = 'D : Deadlift'
      this.BSD = 'Deadlift'
    }
  },
  props: {

  },
  watch: {

  },
  methods: {
    getList () { // 게시물 리스트 가져오기
      axios
        .get('http://localhost:777/bsd/selectList', {
          params: { BSD: this.$route.query.BSD, searchVal: this.searchVal }
        })
        .then((res) => {
          if (res.data.Vlist.length === 0) {
            this.no_show = true
            this.table_show = false
            this.paging_show = false
            document.getElementsByClassName('tableH')[0].style.height = '100px'
          } else {
            this.no_show = false
            this.table_show = true
            this.paging_show = true
            document.getElementsByClassName('tableH')[0].style.height = '474px'
            var listLength = res.data.Vlist.length
            var listSize = this.pageSize
            var page = Math.floor(listLength / listSize)

            if (listLength % listSize > 0) page += 1

            this.pageCount = page
            const start = this.pageNum * this.pageSize
            const end = start + this.pageSize

            this.Vlist = [] // 초기화
            this.Vlist = res.data.Vlist.slice(start, end)
          }
        })
        .catch((error) => {
          console.log(error)
        })
        .finally(() => {
        })
    },
    searchGo () {
      this.pageNum = 0
      this.getList()
    },
    goDetail (item) { // 게시물 클릭시
      this.cntPlus(item.CONTENTS_ID)
      this.$router.push({ name: 'Vdetail', query: { CONTENTS_ID: item.CONTENTS_ID, BSD: this.$route.query.BSD, ID: item.ID } })
    },
    async upload () { // 게시물 업로드
      // valid check
      // 무게
      if (this.weight > 2147483647) {
        alert('범위를 초과하였습니다.')
        return false
      } else if (this.weight < 0) {
        alert('0이상의 숫자를 입력해주세요.')
        return false
      }

      if (isNaN(this.weight)) {
        alert('숫자를 입력해주세요.')
        return false
      }

      if (this.weight === null || this.weight === '') {
        alert('무게를 입력해주세요.')
        return false
      }

      if (this.title.length > 50) {
        alert('제목을 50자 이내로 작성해주세요.')
        return false
      }

      if (this.contents.length > 2000) {
        alert('내용을 2000자 이내로 작성해주세요.')
        return false
      }
      // 파일
      if (this.file_name === null || this.file_name === '') {
        alert('파일을 업로드해주세요.')
        return false
      }

      if (this.file_extend !== 'mp4') {
        alert('mp4 파일을 업로드해주세요.')
        return false
      }

      var today = new Date()
      // 년도 getFullYear()
      var year = today.getFullYear().toString()

      // padStart로 자리수 채우기
      // 월 getMonth() (0~11로 1월이 0으로 표현되기 때문에 + 1을 해주어야 원하는 월을 구할 수 있다.)
      var month = today.getMonth() + 1
      month = month.toString().padStart(2, '0')
      // 일 getDate()
      var date = today.getDate() // 일
      date = date.toString().padStart(2, '0')
      this.date = year + '-' + month + '-' + date

      // 서버에 보낼 데이터 담기
      var frm = new FormData()
      frm.append('uploadFile', this.file, new Blob([this.file_name], { type: 'text/plain' }))
      frm.append('params', new Blob([JSON.stringify({ id: this.$store.getters.getID, title: this.title, contents: this.contents, BSD: this.$route.query.BSD, weight: this.weight, fileName: this.file_name, date: this.date })], { type: 'application/json' }))

      // 서버에 전송
      await axios.post('http://localhost:777/bsd/insertRecord', frm, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
        .then((res) => {
          alert('생성되었습니다.')
          this.$router.push({ name: 'Vdetail', query: { CONTENTS_ID: res.data.result, isNew: 'Y' } })
        })
        .catch((error) => {
          console.log(error)
        })
    },
    handleFileChange (e) { // 올린 파일 이름, 확장자, 파일 추출
      this.file_name = e.target.files[0].name
      this.file = e.target.files[0]
      var fileName = this.file_name
      var length = fileName.length
      var fileDot = fileName.lastIndexOf('.')
      var fileExtend = fileName.substring(fileDot + 1, length).toLocaleLowerCase()
      this.file_extend = fileExtend
    },
    cntPlus (param) {
      // 조회수 +1
      axios
        .get('http://localhost:777/bsd/updateCnt', {
          params: { CONTENTS_ID: param }
        })
        .then((res) => {
        })
        .catch((error) => {
          console.log(error)
        })
        .finally(() => {
        })
    },
    nextPage () { // 다음 버튼
      this.pageNum += 1
      this.getList()
    },
    prevPage () { // 이전 버튼
      this.pageNum -= 1
      this.getList()
    },
    close () { // 닫기 버튼 클릭시 초기화
      this.title = ''
      this.contents = ''
      this.weight = ''
      this.file = ''
      this.file_name = ''
      this.date = ''
      this.modalOpen = false
    }

  }
}
</script>
<style>
.table th {
  padding: 10px;
  color: rgb(251, 251, 251);
  border-bottom: 3px solid rgb(235, 54, 54);
  text-align: center;
}
.table td {
  color: rgb(181, 195, 198);
  padding: 10px;
  border-bottom: 1px solid #bbb7b7;
}

.table tr:hover td {
  color: rgb(233, 231, 92);
}

table {
  width: 100%;
}

.videoList {
  background: #2b2b2b;
  line-height: 22px;
  padding: 40px;
  width: 100%;
  height: 756px;
  min-height: 672px;
}

.head {
  color: #f6f6f6;
}

.comment {
  color: rgb(233, 231, 92);
  text-align: center;
}

.createV {
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

.createV:hover {
  background: yellowgreen;
  background-color: yellow;
}

.align {
  float: right;
}

.search {
  float: left;
}

/* 모달창 */
.big-bg {
  width: 50%;
  height: 83%;
  top : 11% ;
  left: 25%;
  background-color: #1f446a;
  position: fixed;
  padding: 20px;
  position: absolute;
}

.small-bg {
  width: 100%;
    height: 100%;
    background-color: #83b5bc;
    padding: 20px;
    border-radius: 8px;
}

.modal-btn {
  width: 80px;
  height: 30px;
  border-radius: 7px;
  background-color: skyblue
}

.modal-btn:hover {
  cursor: pointer;
  background-color: yellowgreen
}

.write {
  text-align: left;
  line-height: 50px;
  padding: 20px;
}

.input {
  width: 80%;
  height: 32px;
  font-size: 15px;
  border: 0;
  border-radius: 15px;
  outline: none;
  padding-left: 10px;
  background-color: rgb(233, 233, 233);
  text-align: center;
}

.textarea {
  font-size: 15px;
  border: 0;
  border-radius: 15px;
  outline: none;
  padding-left: 10px;
  background-color: rgb(233, 233, 233);
  text-align: center;
  width: 80%;
  height: 211px;
}

.label {
  display: inline-block;
  width: 10%;
  text-align: center;
  vertical-align: top;
}

.noData {
  color: cyan;
  display: table-cell;
  vertical-align: middle;
  text-align: center;
}

.noData {
  display: inline-block;
}

.page-count {
  color: yellow;
}

.page-btn {
  width: 80px;
  height: 30px;
  border-radius: 7px;
  background-color: turquoise
}

.page-btn:hover {
  cursor: pointer;
  background-color: yellowgreen
}

.tableH {
  height: 474px;
}

.tableH2 {
  height: 100px;
}

.searchBar {
  width: 300px;
  height: 38px;
  font-size: 20px;
  display: inline-block;
  vertical-align: middle;
  text-align: center;
  margin: 0.25rem;
  padding: 0.5rem 1rem;
  text-decoration: none;
}
</style>
