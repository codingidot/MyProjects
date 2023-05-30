<template>
<div class="videoList">
    <h1 class="comment">{{ Comment }}</h1>
    <span class="search"><input type="text" class="searchBar" v-model="searchVal"  placeholder="  search" /><li class="createV" @click="searchGo"><a href="#">검색</a></li></span>
    <span class="align"><li class="createV" v-on:click="deleteGo"><a href="#">삭제</a></li></span>
    <div class="tableH">
    <table id="table" class="table" v-if="table_show">
        <thead class="head">
        <tr>
            <th style="width:5%">선택</th>
            <th>ID</th>
            <th>종목</th>
            <th style="width:7%">인정여부</th>
            <th style="width:15%">닉네임</th>
            <th style="width:45%">제목</th>
            <th style="width:8%">날짜</th>
            <th style="width:5%">무게</th>
            <th style="width:3%">조회수</th>
        </tr>
        </thead>
        <tbody id="tableBody">
        <tr v-for="(item, idx) in Vlist" v-bind:key="idx" class="row">
            <td>
                <input type="checkbox"
                        :id="'check_' + idx"
                        :value="item.CONTENTS_ID"
                        v-model="selected"
                >
            </td>
            <td>{{item.CONTENTS_ID}}</td>
            <td v-if="item.BSD == 'B'">Bench press</td><td v-if="item.BSD == 'S'">Squat</td><td v-if="item.BSD == 'D'">Deadlift</td>
            <td class="cell" @click="goDetail(item)">{{item.CL_RESULT}}</td>
            <td class="cell" @click="goDetail(item)">{{item.NICKNAME}}</td>
            <td class="cell" @click="goDetail(item)">{{item.TITLE}}</td>
            <td class="cell" @click="goDetail(item)">{{item.UPLOAD_DATE}}</td>
            <td class="cell" @click="goDetail(item)">{{item.WEIGHT}}</td>
            <td class="cell" @click="goDetail(item)">{{item.CNT}}</td>
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
</div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'AdminVideoView',
  data () {
    return {
      no_show: false,
      table_show: true,
      Vlist: [{}],
      Comment: '게시물 관리',
      pageNum: 0,
      pageSize: 10,
      pageCount: 0,
      searchVal: '',
      selected: [],
      paging_show: true
    }
  },
  mounted () {
    this.getList()
  },
  props: {

  },
  watch: {

  },
  methods: {
    getList () { // 게시물 리스트 가져오기
      axios// 116.43.52.214
        .get('http://localhost:777/bsd/selectAdminVList', {
          params: { searchVal: this.searchVal }
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
    async getFileName (contentsId) {
      // 콘텐츠 id로 정보 불러옴
      await axios
        .get('http://localhost:777/bsd/selectDetail', {
          params: { CONTENTS_ID: contentsId }
        })
        .then((res) => {
          console.log(res)
          this.attachment = res.data.Vdetail[0].ATTACHMENT // 스프링부트 서버 기준으로 경로 설정해야함
        })
        .catch((error) => {
          console.log(error)
        })
        .finally(() => {
        })
    },
    async deleteFile () {
      // 파일 삭제
      await axios({
        method: 'post',
        url: 'http://localhost:777/bsd/deleteFile',
        data: {
          fileName: this.attachment
        }
      })
    },
    async deleteGo () {
      var arr = []
      arr = this.selected

      if (arr.length === 0) {
        alert('삭제할 게시물을 선택해주세요.')
        return false
      }
      for (var i = 0; i < arr.length; i++) {
        await this.getFileName(arr[i])
        // 게시물 삭제
        await axios({
          method: 'post',
          url: 'http://localhost:777/bsd/deleteContents',
          data: {
            CONTENTS_ID: arr[i]
          }
        })
        await this.deleteFile()
      }
      this.selected = []
      alert('삭제되었습니다.')
      this.getList()
    },
    searchGo () {
      this.pageNum = 0
      this.getList()
    },
    goDetail (item) { // 게시물 클릭시
      this.$router.push({ name: 'Vdetail', query: { CONTENTS_ID: item.CONTENTS_ID, BSD: this.$route.query.BSD, ID: item.ID } })
    },
    nextPage () { // 다음 버튼
      this.pageNum += 1
      this.getList()
    },
    prevPage () { // 이전 버튼
      this.pageNum -= 1
      this.getList()
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
