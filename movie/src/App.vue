<template>
  <div id="app">
    

    <router-view/>
  </div>
</template>

<script>

export default{

 name:'APP',


}
</script>


<style>
.nav-top{
  width: 100%;
  /* height: 10px; */
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}

</style>
