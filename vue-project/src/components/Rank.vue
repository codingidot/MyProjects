<template>
    <div class="rankList">
      <h1 class="comment">{{ Comment }}</h1><br><br><br>
      <div class="checks etrans">
        <span class="search">
            <input type="checkbox" id="ex_chk1" v-model="BSDarr" value="Benchpress">
            <label for="ex_chk1">Benchpress</label> &nbsp;
            <input type="checkbox" id="ex_chk2" v-model="BSDarr" value="Squat">
            <label for="ex_chk2">Squat</label> &nbsp;
            <input type="checkbox" id="ex_chk3" v-model="BSDarr" value="Deadlift">
            <label for="ex_chk3">Deadlift</label> &nbsp;
            <input type="text" class="searchBar" v-model="searchVal"  placeholder="search by nickname" /><li class="createV" @click="searchRank"><a href="#">검색</a></li>
        </span><br><br><br>
      </div>
      <div class="tableH">
        <table id="table" class="table" v-if="table_show">
          <thead class="head">
            <tr>
                <th v-show=false>게시물 ID</th>
                <th style="width:10%">랭킹</th>
                <th style="width:30%">닉네임</th>
                <th style="width:30%">종목</th>
                <th style="width:30%">총 무게(kg)</th>
            </tr>
          </thead>
          <tbody id="tableBody">
            <tr v-for="(item, idx) in Rlist" v-bind:key="idx" class="row">
                <td class="cell" v-show=false>{{ item.ID }}</td>
                <td class="cell" >{{ item.RANKING }}</td>
                <td class="cell" >{{ item.NICKNAME }}</td>
                <td class="cell" >{{ BSDString }}</td>
                <td class="cell" >{{ item.WEIGHT }}</td>
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
    </div>
  </template>

<script>
import axios from 'axios'
export default {
  name: 'RankList',
  data () {
    return {
      no_show: false,
      table_show: true,
      Rlist: [{}],
      Comment: 'Ranking',
      BSDarr: [],
      B: '',
      S: '',
      D: '',
      ID: '',
      BSDString: '',
      searchVal: ''
    }
  },
  mounted () {
    this.ID = this.$route.query.ID
  },
  props: {

  },
  watch: {

  },
  methods: {
    searchRank () { // 게시물 리스트 가져오기
      // 초기화
      this.BSDString = ''
      this.B = ''
      this.S = ''
      this.D = ''

      var arr = this.BSDarr

      for (var i = 0; i < arr.length; i++) {
        this.BSDString += arr[i] + '    '
        if (arr[i] === 'Benchpress') {
          this.B = 'B'
        } else if (arr[i] === 'Squat') {
          this.S = 'S'
        } else if (arr[i] === 'Deadlift') {
          this.D = 'D'
        }
      }

      axios
        .get('http://localhost:777/bsd/selectRank', {
          params: { B: this.B, S: this.S, D: this.D, ID: this.ID, NICKNAME: this.searchVal }
        })
        .then((res) => {
          if (res.data.Rlist.length === 0) {
            this.no_show = true
            this.table_show = false
            document.getElementsByClassName('tableH')[0].style.height = '100px'
          } else {
            this.no_show = false
            this.table_show = true
            document.getElementsByClassName('tableH')[0].style.height = '474px'

            this.Rlist = res.data.Rlist
          }
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

.rankList {
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

/*체크박스*/
.checks {
    position: relative;
    color: antiquewhite;
}

.checks input[type="checkbox"] {  /* 실제 체크박스는 화면에서 숨김 */
  position: absolute;
  width: 1px;
  height: 1px;
  padding: 0;
  margin: -1px;
  overflow: hidden;
  clip:rect(0,0,0,0);
  border: 0
}
.checks input[type="checkbox"] + label {
  display: inline-block;
  position: relative;
  cursor: pointer;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
}
.checks input[type="checkbox"] + label:before {  /* 가짜 체크박스 */
  content: ' ';
  display: inline-block;
  width: 21px;  /* 체크박스의 너비를 지정 */
  height: 21px;  /* 체크박스의 높이를 지정 */
  line-height: 21px; /* 세로정렬을 위해 높이값과 일치 */
  margin: -2px 8px 0 0;
  text-align: center;
  vertical-align: middle;
  background: #fafafa;
  border: 1px solid #cacece;
  border-radius : 3px;
  box-shadow: 0px 1px 2px rgba(0,0,0,0.05), inset 0px -15px 10px -12px rgba(0,0,0,0.05);
}
.checks input[type="checkbox"] + label:active:before,
.checks input[type="checkbox"]:checked + label:active:before {
  box-shadow: 0 1px 2px rgba(0,0,0,0.05), inset 0px 1px 3px rgba(0,0,0,0.1);
}

.checks input[type="checkbox"]:checked + label:before {  /* 체크박스를 체크했을때 */
  content: '\2714';  /* 체크표시 유니코드 사용 */
  color: #99a1a7;
  text-shadow: 1px 1px #fff;
  background: #e9ecee;
  border-color: #adb8c0;
  box-shadow: 0px 1px 2px rgba(0,0,0,0.05), inset 0px -15px 10px -12px rgba(0,0,0,0.05), inset 15px 10px -12px rgba(255,255,255,0.1);
}

.checks.small input[type="checkbox"] + label {
  font-size: 12px;
}

.checks.small input[type="checkbox"] + label:before {
  width: 17px;
  height: 17px;
  line-height: 17px;
  font-size: 11px;
}

.checks.etrans input[type="checkbox"] + label {
  padding-left: 30px;
}
.checks.etrans input[type="checkbox"] + label:before {
  position: absolute;
  left: 0;
  top: 0;
  margin-top: 0;
  opacity: .6;
  box-shadow: none;
  border-color: #6cc0e5;
  -webkit-transition: all .12s, border-color .08s;
  transition: all .12s, border-color .08s;
}

.checks.etrans input[type="checkbox"]:checked + label:before {
  position: absolute;
  content: "";
  width: 10px;
  top: -5px;
  left: 5px;
  border-radius: 0;
  opacity:1;
  background: transparent;
  border-color:transparent #6cc0e5 #6cc0e5 transparent;
  border-top-color:transparent;
  border-left-color:transparent;
  -ms-transform:rotate(45deg);
  -webkit-transform:rotate(45deg);
  transform:rotate(45deg);
}

.no-csstransforms .checks.etrans input[type="checkbox"]:checked + label:before {
  /*content:"\2713";*/
  content: "\2714";
  top: 0;
  left: 0;
  width: 21px;
  line-height: 21px;
  color: #6cc0e5;
  text-align: center;
  border: 1px solid #6cc0e5;
}
</style>
