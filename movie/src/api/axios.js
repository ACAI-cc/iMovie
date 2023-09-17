import axios from 'axios'


// create an axios instance
const service = axios.create({
  baseURL:'/api', // url = base url + request url
  // withCredentials: true, // send cookies when cross-domain requests
  timeout: 5000 // request timeout
})

// request interceptor
// service.interceptors.request.use(
//   config => {
//    // 判断携带token
//    if (store.state.user.token) {
//     config.headers.token = store.state.user.token;
// }
//     return config
//   },
//   error => {
//     // do something with request error
//     console.log(error) // for debug
//     return Promise.reject(error)
//   }
// )

export default service
