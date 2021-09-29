<template>
  <div class="hello">
    <h1>Hello FoodIndex</h1>
    <ul  v-for="food in foods" :key="food.id">
      <li>{{ food.name }}</li>
      <li>{{ food.expireDate }}</li>
    </ul>
    <!--ToDo: 一覧の並べ替え？ -->
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'FoodIndex',
   data () {
      return {
      foods: null,
      }
  },
  mounted () {
      axios
      .get('/food/')
      .then(response => (this.foods = response.data))

      // 追加？
      // 更新？
      // 削除？
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
