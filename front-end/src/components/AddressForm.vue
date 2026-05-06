<template>
  <div class="address-form">
    <van-form @submit="handleSubmit">
      <van-cell-group>
        <van-field
          v-model="form.consigneeName"
          name="consigneeName"
          label="收货人"
          placeholder="请输入收货人姓名"
          :rules="[{ required: true, message: '请输入收货人姓名' }]"
        />
        <van-field
          v-model="form.consigneePhone"
          name="consigneePhone"
          label="手机号"
          placeholder="请输入手机号"
          :rules="[
            { required: true, message: '请输入手机号' },
            { pattern: /^1[3-9]\d{9}$/, message: '手机号格式不正确' }
          ]"
        />
        <van-field
          v-model="form.province"
          name="province"
          label="省份"
          placeholder="请输入省份"
          :rules="[{ required: true, message: '请输入省份' }]"
        />
        <van-field
          v-model="form.city"
          name="city"
          label="城市"
          placeholder="请输入城市"
          :rules="[{ required: true, message: '请输入城市' }]"
        />
        <van-field
          v-model="form.district"
          name="district"
          label="区县"
          placeholder="请输入区县"
          :rules="[{ required: true, message: '请输入区县' }]"
        />
        <van-field
          v-model="form.detailAddress"
          name="detailAddress"
          label="详细地址"
          type="textarea"
          placeholder="请输入详细地址"
          rows="2"
          :rules="[{ required: true, message: '请输入详细地址' }]"
        />
        <van-field
          v-model="form.zipCode"
          name="zipCode"
          label="邮编"
          placeholder="请输入邮编"
          :rules="[
            { required: true, message: '请输入邮编' },
            { pattern: /^\d{6}$/, message: '邮编格式不正确' }
          ]"
        />
        <van-field name="isDefault" label="设为默认">
          <template #input>
            <van-switch v-model="form.isDefault" size="20" />
          </template>
        </van-field>
      </van-cell-group>

      <div class="form-button">
        <van-button type="primary" block native-type="submit" :loading="loading">
          保存
        </van-button>
      </div>
    </van-form>
  </div>
</template>

<script>
/**
 * 地址表单组件
 * 用于新增和编辑收货地址
 */
export default {
  name: 'AddressForm',

  props: {
    // 地址ID（编辑时传入）
    addressId: {
      type: [Number, String],
      default: null
    },
    // 地址信息（编辑时传入）
    address: {
      type: Object,
      default: () => ({})
    }
  },

  data() {
    return {
      // 表单数据
      form: {
        consigneeName: '',
        consigneePhone: '',
        province: '',
        city: '',
        district: '',
        detailAddress: '',
        zipCode: '',
        isDefault: false
      },
      // 加载状态
      loading: false
    }
  },

  created() {
    // 如果是编辑模式，填充表单数据
    if (this.addressId) {
      this.fillForm()
    }
  },

  methods: {
    /**
     * 填充表单数据
     */
    fillForm() {
      Object.keys(this.form).forEach(key => {
        if (this.address[key] !== undefined) {
          this.form[key] = this.address[key]
        }
      })
    },

    /**
     * 提交表单
     */
    handleSubmit() {
      this.loading = true

      const api = this.addressId ? 'updateAddress' : 'addAddress'
      const data = {
        ...this.form,
        id: this.addressId
      }

      this.$api[api](data).then(res => {
        if (res.code === 200) {
          this.$toast.success(this.addressId ? '修改成功' : '添加成功')
          this.$emit('success')
          this.$router.back()
        } else {
          this.$toast.fail(res.message)
        }
      }).finally(() => {
        this.loading = false
      })
    }
  }
}
</script>

<style scoped>
/**
 * @联系作者：16768118056
 * @描述：（此项目非免费分享）源码百分百可用，搞定毕设不发愁，支持远程调试安装、二次开发、定制、讲解、文档类。
 * @访问：https://www.notmaker.com/detail/8525d865acb24d55890cc81245135363/gtt20260415，查看完整运行演示。
 */
.address-form {
  padding: 15px;
}

.form-button {
  margin-top: 20px;
}
</style>
