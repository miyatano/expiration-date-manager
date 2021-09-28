<template>
  <div class="hello">
    <h1>Hello FoodIndex</h1>
      {{ info }}
      {{ data }}
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'FoodIndex',
   data () {
      return {
      data: [
          {
              "id": 3,
              "name": "はちみつ",
              "expireDate": "2022/12/01"
          },
          {
              "id": 2,
              "name": "レトルトカレー",
              "expireDate": "2022/01/22"
          },
          {
              "id": 10,
              "name": "レトルトカレー",
              "expireDate": "2023/02/02"
          }
      ],
      info: null
      }
  }
  ,
  mounted () {
      axios
      .get('https://api.coindesk.com/v1/bpi/currentprice.json')
      .then(response => (this.info = response))
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
