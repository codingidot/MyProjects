<template>
    <div class="memberList">
      <h1 class="comment">{{ Comment }}</h1><br><br><br>
        <span class="search">
            <input type="text" class="searchBar" v-model="searchVal"  placeholder="search by nickname" /><li class="createV" @click="getMemberList"><a href="#">검색</a></li>
        </span>
        <span class="align"><li class="createV" v-on:click="deleteGo"><a href="#">삭제</a></li></span>
      <div class="tableH">
        <table id="table" class="table" v-if="table_show">
          <thead class="head">
            <tr>
                <th style="width:8%">선택</th>
                <th >ID</th>
                <th style="width:10%">이름</th>
                <th style="width:30%">닉네임</th>
                <th style="width:30%">전화번호</th>
            </tr>
          </thead>
          <tbody id="tableBody">
            <tr v-for="(item, idx) in Mlist" v-bind:key="idx" class="row">
                <td>
                  <input type="checkbox"
                          :id="'check_' + idx"
                          :value="item.ID"
                          v-model="selected"
                  >
                </td>
                <td class="cell" @click="goPersonVlist(item)">{{ item.ID }}</td>
                <td class="cell" @click="goPersonVlist(item)">{{ item.NAME }}</td>
                <td class="cell" @click="goPersonVlist(item)">{{ item.NICKNAME }}</td>
                <td class="cell" @click="goPersonVlist(item)">{{ item.PH }}</td>
            </tr>
          </tbody>
        </table>
      </div>
      <br>
      <br>
      <br>
      <div class="noData"><h1 v-show="no_show" class="no">데이터가 없습니다.</h1></div>

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
  name: 'AdminMemberView',
  data () {
    return {
      no_show: false,
      table_show: true,
      Mlist: [{}],
      Comment: '회원관리',
      searchVal: '',
      selected: [],
      paging_show: true,
      pageNum: 0,
      pageSize: 10,
      pageCount: 0
    }
  },
  mounted () {
    this.getMemberList()
  },
  props: {

  },
  watch: {

  },
  methods: {
    getMemberList () { // 게시물 리스트 가져오기
      axios// localhost
        .get('http://localhost:777/bsd/selectAdminMList', {
          params: { searchVal: this.searchVal }
        })
        .then((res) => {
          if (res.data.Mlist.length === 0) {
            this.no_show = true
            this.table_show = false
            this.paging_show = false
            document.getElementsByClassName('tableH')[0].style.height = '100px'
          } else {
            this.no_show = false
            this.table_show = true
            this.paging_show = true
            document.getElementsByClassName('tableH')[0].style.height = '474px'

            var listLength = res.data.Mlist.length
            var listSize = this.pageSize
            var page = Math.floor(listLength / listSize)

            if (listLength % listSize > 0) page += 1

            this.pageCount = page
            const start = this.pageNum * this.pageSize
            const end = start + this.pageSize

            this.Mlist = [] // 초기화
            this.Mlist = res.data.Mlist.slice(start, end)
          }
        })
        .catch((error) => {
          console.log(error)
        })
        .finally(() => {
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
        // 회원 삭제
        await axios({
          method: 'post',
          url: 'http://localhost:777/bsd/deleteMember',
          data: {
            ID: arr[i]
          }
        })
      }

      this.selected = []
      alert('삭제되었습니다.')
      this.getMemberList()
    },
    nextPage () { // 다음 버튼
      this.pageNum += 1
      this.getMemberList()
    },
    prevPage () { // 이전 버튼
      this.pageNum -= 1
      this.getMemberList()
    },
    goPersonVlist (item) {
      this.$router.push({ name: 'MyPage', query: { PersonID: item.ID } })
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

.memberList {
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
</style>
