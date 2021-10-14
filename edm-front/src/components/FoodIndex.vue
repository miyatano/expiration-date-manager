<template>
  <div class="hello">
    <div class="common-layout">
    <el-container>
      <el-header>
        <h1>賞味期限までに食べましょう</h1>
      </el-header>
      <el-main>
        <el-row class="row-bg" justify="center">
          <el-col :span="16">
            <div class="grid-content bg-purple-light">
              <el-form ref="form" label-width="120px">
                <el-form-item label="食品名">
                  <el-input v-model="foodName"></el-input>
                </el-form-item>
                <el-form-item label="賞味期限の日付">
                  <el-input v-model="expireDate"></el-input>
                </el-form-item>
                <el-button type="primary" @click="postFood">Primary</el-button>
              </el-form>
            </div>
            <div class="grid-content bg-purple-light">
              <el-table
                :data="foods"
                style="width: 100%"
              >
                <el-table-column prop="name" label="食品名" sortable width="180" />
                <el-table-column prop="expireDate" label="賞味期限の日付" width="180" />
                <el-table-column prop="address" label="Status" :formatter="formatter" />
              </el-table>
            </div>
          </el-col>
        </el-row>


      </el-main>
    </el-container>
    </div>

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
    postFood () {
      const item = {
          "name": this.foodName,
          "expireDate": this.expireDate
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

</style>
