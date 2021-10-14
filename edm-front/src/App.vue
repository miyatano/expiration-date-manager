<template>
  <div>
  <!-- <HelloWorld msg="Welcome to Your Vue.js App"/> -->
  <FoodIndex/>
  </div>
</template>

<script>
// import HelloWorld from './components/HelloWorld.vue'
import FoodIndex from './components/FoodIndex.vue'

export default {
  name: 'App',
  components: {
    FoodIndex
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
