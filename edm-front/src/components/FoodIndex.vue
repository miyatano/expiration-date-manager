<template>
  <div class="hello">
    <h1>Hello FoodIndex</h1>
    <ul  v-for="food in foods" :key="food.id">
      <li>{{ food.name }}</li>
      <li>{{ food.expireDate }}</li>
      <button @click="deleteFood(food.id)">DELETE THIS ITEM!</button>
      <button @click="consoleLogId(food.id)">See what happen in console.log</button>
    </ul>

    <h2>お試しのアイテム</h2>
    <button @click="postSoup">スープ増えるよ :)</button>
    <button @click="increment">Up vote</button>
    <p>{{ count }}</p>


    <input v-model="message" placeholder="edit me" />
    <p>Message is: {{ message }}</p>
    <button @click="consoleLogMessage(message)">See what happen in console.log</button>
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
        message: "",
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
    consoleLogMessage(message) {
      console.log(`Here is message from input: ${message}`);
    },
    getAll () {
      axios
      .get('/food/')
      .then(response => (this.foods = response.data))
    },
    postSoup () {
      const item = {
          "name":"スープ",
          "expireDate": "2023/02/02"
      };
      axios
      .post('/food/', item)
      .then(() => { this.getAll(); })
    },
    postFood () {
      const item = {
          "name":"スープ",
          "expireDate": "2023/02/02"
      };
      axios
      .post('/food/', item)
      .then(() => { this.getAll(); })
    },
    deleteFood (id) {
      axios
      .delete(`/food/${id}`)
      .then(() => { this.getAll(); })
    }
  },
  mounted () {
      this.getAll();
      console.log(this);

      // 追加？
      // 更新？
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
