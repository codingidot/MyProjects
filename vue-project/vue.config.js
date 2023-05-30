const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,

  outputDir : "../../workSpace/BSD/src/main/resources/static",
  devServer : {
    proxy :{
      '/*': {
        target: "http://localhost:80"
      }
    }
  }
})
