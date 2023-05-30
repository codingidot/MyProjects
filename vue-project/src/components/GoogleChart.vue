<template>
<div class="chart">
    <div>
        <h2>{{ voting }}</h2><br><br>
        <GChart
        type="PieChart"
        :options="options"
        :data="data"
        />
    </div>
</div>
</template>

<script>
import axios from 'axios'
import { GChart } from 'vue-google-charts'
export default {
  name: 'googleChart',
  components: {
    GChart
  },
  data () {
    return {
      data: [],
      options: {
        width: 1608,
        height: 400
      },
      voting: '',
      finishVoteCount: 3
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
    this.getVoteResult()
  },
  // Updating
  beforeUpdate () {
  },
  updated () {
  },
  props: { // 부모 컴포넌트로부터 받은 파라미터
    contentsId: Number
  },
  watch: {

  },
  methods: {
    async getVoteResult () {
      console.log('가져온다')
      // 투표결과 가져오기
      axios
        .get('http://localhost:777/bsd/selectVoteResult', {
          params: { CONTENTS_ID: this.contentsId }
        })
        .then((res) => {
          this.data = [
            ['Agree_Disagree', 'YesNo'],
            ['인정', res.data.Vlist[0].YES],
            ['노인정', res.data.Vlist[0].NONO]
          ]
          console.log(res)
          if (res.data.Vlist[0].TOTAL === 0) {
            this.voting = '아직 아무도 투표하지 않았습니다.'
            this.data = [
              ['Agree_Disagree', 'YesNo'],
              ['인정', 1],
              ['노인정', 1]
            ]
          } else if (res.data.Vlist[0].TOTAL >= this.finishVoteCount) {
            this.voting = '투표완료'
          } else {
            this.voting = '투표중'
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
