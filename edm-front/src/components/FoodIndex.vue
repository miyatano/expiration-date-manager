<template>
  <div class="hello">
    <h1>Hello FoodIndex</h1>
    <ul  v-for="food in foods" :key="food.id">
      <li>{{ food.name }}</li>
      <li>{{ food.expireDate }}</li>
    </ul>

    <input v-model="foodName" placeholder="Food name" />
    <input v-model="expireDate" placeholder="expire date" />
    <button @click="postFood(foodName, expireDate)">Post</button>

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
        message: '',
        foodName: '',
        expireDate: '',
        foods: null,
      }
  },
  methods: {
    getAll () {
      axios
      .get('/food/')
      .then(response => (this.foods = response.data))
    },
    postFood (foodName, expireDate) {
      const item = {
          "name": foodName,
          "expireDate": expireDate
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
