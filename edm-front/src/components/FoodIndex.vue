<template>
  <div class="hello">
    <h1>Hello FoodIndex</h1>
    <ul  v-for="food in foods" :key="food.id">
      <li>{{ food.name }}</li>
      <li>{{ food.expireDate }}</li>
      <button @click="consoleLogId(food.id)">See what happen in console.log</button>
    </ul>
    <button @click="deleteFood">Delete id:1</button>

    <button @click="increment">Up vote</button>
    <p>{{ count }}</p>
    <!--ToDo: 一覧の並べ替え？ -->
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'FoodIndex',
   data () {
      return {
        count: 4,
        foods: null,
      }
  },
  methods: {
    increment () {
      this.count++;
    },
    consoleLogId (id) {
      console.log("This item is no." + id);
    },
    getAll () {
      axios
      .get('/food/')
      .then(response => (this.foods = response.data))
    },
    deleteFood () {
      axios
      .delete('/food/1')
      .then(() => { this.getAll(); })
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
