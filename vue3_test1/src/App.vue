<template>
  <div>
    <button @click="inc">increment</button>
    {{ count }}+5={{ count5 }}
  </div>
  
</template>


<script>
/* eslint-disable */
import { computed, ref } from 'vue';

export default {
  name: 'App',
  components: {
   
  },
  setup(){
    const count=ref(0);
    const count5=computed(()=>count.value + 5 );
    const inc=()=>{
      count.value++;
    }

    return {inc, count5};
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
