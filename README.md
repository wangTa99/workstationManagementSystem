## 项目介绍

<p align="center">
  <a href="#" style="border-radius: 50%;width: 100px;height: 100px">
    <img src="front-end/public/favicon.ico" alt="企业级工位管理系统" >
  </a>
</p>


<p align="center">
   <a target="_blank" href="#">
      <img src="https://img.shields.io/hexpm/l/plug.svg"/>
      <img src="https://img.shields.io/badge/JDK-1.8+-green.svg"/>
      <img src="https://img.shields.io/badge/springboot-2.3.5.RELEASE-green"/>
      <img src="https://img.shields.io/badge/vue-2.6.11-green"/>
      <img src="https://img.shields.io/badge/mysql-8.0.20-green"/>
      <img src="https://img.shields.io/badge/mybatis--plus-3.3.1-green"/>
      <img src="https://img.shields.io/badge/element--ui-2.15.13-green"/>
   </a>
</p>

[技术介绍](#技术介绍) | [功能描述](#功能描述) | [部分截图](#部分截图) | [目录结构](#目录结构) | [项目特点](#项目特点) | [功能演示](#功能演示)

**注意**: 测试账号请查看 `ddl.sql` 文件中的初始数据

---

## 技术介绍

### 后端技术栈

| 技术         | 说明           | 官网                                   |
| ------------ | -------------- | -------------------------------------- |
| Spring Boot  | 容器 + MVC框架 | https://spring.io/projects/spring-boot |
| MyBatis-Plus | ORM框架        | https://baomidou.com                   |
| MySQL        | 数据库         | https://www.mysql.com                  |
| JWT          | JWT登录支持    | https://jwt.io                         |
| Hutool       | Java工具类库   | https://hutool.cn                      |
| POI          | Excel导入导出  | https://poi.apache.org                 |

### 前端技术栈

| 技术       | 说明     | 官网                     |
| ---------- | -------- | ------------------------ |
| Vue        | 前端框架 | https://cn.vuejs.org     |
| Vue Router | 路由框架 | https://router.vuejs.org |
| Axios      | HTTP框架 | https://axios-http.com   |
| Element UI | UI框架   | https://element.eleme.cn |

### 小程序技术栈

| 技术       | 说明       | 官网                                                       |
| ---------- | ---------- | ---------------------------------------------------------- |
| 微信小程序 | 小程序框架 | https://developers.weixin.qq.com/miniprogram/dev/framework |

---

## 功能描述

系统分为（身份）：员工、管理员。
**员工功能：**
- 注册登录
- 个人中心
- 部门信息查看
- 工位分配查看

**管理员功能：**
- 个人中心
- 员工信息管理
- 部门信息管理
- 工位信息管理
- 使用情况管理
- 工位分配管理

###

---

## 部分截图

![PixPin_20251026_213943.png](https://store.ptcc9.top/notmaker/user_upload/b8c9836261f84186837a6dbfa516a867/2025-10-26%2021:40:02_PixPin_2025-10-26_21-39-43.png)

![PixPin_20251026_213936.png](https://store.ptcc9.top/notmaker/user_upload/b8c9836261f84186837a6dbfa516a867/2025-10-26%2021:40:02_PixPin_2025-10-26_21-39-36.png)

![PixPin_20251026_213950.png](https://store.ptcc9.top/notmaker/user_upload/b8c9836261f84186837a6dbfa516a867/2025-10-26%2021:40:02_PixPin_2025-10-26_21-39-50.png)

![PixPin_20251026_213747.png](https://store.ptcc9.top/notmaker/user_upload/b8c9836261f84186837a6dbfa516a867/2025-10-26%2021:39:08_PixPin_2025-10-26_21-37-47.png)

![PixPin_20251026_213800.png](https://store.ptcc9.top/notmaker/user_upload/b8c9836261f84186837a6dbfa516a867/2025-10-26%2021:39:08_PixPin_2025-10-26_21-38-00.png)

---

## 目录结构

```
├── back-end/                    # 后端项目
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── com/notmaker/
│   │       │       ├── controller/      # 控制器层
│   │       │       ├── service/         # 服务层
│   │       │       ├── mapper/          # 数据访问层
│   │       │       ├── entity/          # 实体类
│   │       │       ├── config/          # 配置类
│   │       │       └── SpringBootStarter.java  # 启动类
│   │       └── resources/
│   │           ├── mapper/              # XML映射文件
│   │           └── application.yml      # 配置文件
│   └── pom.xml                  # Maven依赖配置
│
├── ddl.sql                      # 数据库建表脚本
│
├── front-end/                   # 前端项目
│   ├── src/
│   │   ├── views/               # 页面
│   │   ├── router/              # 路由配置
│   │   ├── api/                 # 接口封装
│   │   ├── assets/              # 静态资源
│   │   ├── App.vue              # 根组件
│   │   └── main.js              # 入口文件
│   ├── public/
│   │   └── index.html
│   ├── package.json             # 依赖配置
│   └── vue.config.js            # Vue配置
│
├── wechat/                      # 微信小程序
│   ├── pages/                   # 小程序页面
│   ├── utils/                   # 工具类
│   ├── assets/                  # 静态资源
│   ├── app.js                   # 小程序入口
│   ├── app.json                 # 小程序配置
│   └── app.wxss                 # 全局样式
│
└── README.md                    # 项目说明文档
```

---

## 项目特点

- **前后端分离**：RESTful接口设计，Axios统一封装请求
- **组件化实现**：Vue组件+Element UI构建可复用UI模块
- **安全认证**：基于JWT的登录态管理，拦截器统一处理
- **便捷导入导出**：借助POI完成Excel的数据导入与导出
- **示例完备**：提供完整DDL与示例数据，开箱即用

---


## 运行环境

- **JDK**: 1.8+
- **MySQL**: 8.0+
- **Node.js**: 12.0+
- **Maven**: 3.0+

---

## 开发工具

- **后端**: IDEA / Eclipse
- **前端**: VSCode / WebStorm
- **数据库**: Navicat / MySQL Workbench
- **小程序**: 微信开发者工具

---

## 运行步骤

### 1. 数据库准备

```bash
# 创建数据库并导入表结构和数据
执行 ddl.sql 文件
```

### 2. 后端启动

```bash
# 进入后端目录
cd back-end

# 修改数据库配置
编辑 src/main/resources/application.yml
修改数据库连接信息（url、username、password）

# 启动后端项目
运行 SpringBootStarter.java 主类

# 默认访问地址
http://localhost:18003
```

### 3. 前端启动

```bash
# 进入前端目录
cd front-end

# 安装依赖
npm install

# 启动前端项目
npm run serve

# 默认访问地址
http://localhost:8080
```

> 若部署至服务器，请同步修改 `src/api/axios.js` 中的 `baseURL`。

### 4. 小程序启动

```bash
# 使用微信开发者工具打开 wechat 目录
# 修改 utils/api.js 中后端地址
# 编译运行即可体验
```

---

## 默认账号

> 更多示例账号可参考 `ddl.sql` 中的初始化数据。

---

## 许可证

本项目采用 Apache License 2.0 许可证

---

## 注意事项

1. 本项目用于学习与演示，代码以可读性为首要目标
2. 当前密码为明文存储，正式环境需替换为加密方案
3. 首次运行请确保文件上传目录已创建或在配置中修改
4. 数据库字符集建议使用 `utf8mb4`
5. 部署线上时请更新配置中的域名、端口及静态资源路径
