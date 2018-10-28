<template>
  <el-table
    :data="tableData5"
    style="width: 100%"
    :expand-row-keys="expands"
    row-key="id"
    @row-click="rowClick"
    >
    <el-table-column type="expand" fixed>
      <template slot-scope="props">
        <el-form label-position="left" inline class="demo-table-expand">
          <el-form-item label="商品名称">
            <span>{{ props.row.name }}</span>
          </el-form-item>
          <el-form-item label="所属店铺">
            <span>{{ props.row.shop }}</span>
          </el-form-item>
          <el-form-item label="商品 ID">
            <span>{{ props.row.id }}</span>
          </el-form-item>
          <el-form-item label="店铺 ID">
            <span>{{ props.row.shopId }}</span>
          </el-form-item>
          <el-form-item label="商品分类">
            <span>{{ props.row.category }}</span>
          </el-form-item>
          <el-form-item label="店铺地址">
            <span>{{ props.row.address }}</span>
          </el-form-item>
          <el-form-item label="商品描述">
            <span>{{ props.row.desc }}</span>
          </el-form-item>
        </el-form>
      </template>
    </el-table-column>
    <!--<el-table-column-->
      <!--label="商品 ID"-->
      <!--prop="id">-->
    <!--</el-table-column>-->
    <el-table-column
      label="案号">
    </el-table-column>
    <el-table-column
      label="案由">
    </el-table-column>

    <el-table-column
      label="案名">
    </el-table-column>
    <el-table-column
      label="立案日期">
    </el-table-column>
    <el-table-column
      label="开庭日期">
    </el-table-column>
    <el-table-column
      label="保全">
    </el-table-column>
    <el-table-column
      prop="tag"
      label="标签"
      width="100"
      :filters="[{ text: '家', value: '家' }, { text: '公司', value: '公司' }]"
      :filter-method="filterTag"
      filter-placement="bottom-end">
      <template slot-scope="scope">

        <el-tag v-if="scope.row.tag ==='家'" style="success">{{scope.row.tag}}</el-tag>
      </template>
    </el-table-column>

  </el-table>
</template>

<script>
  export default {
    data() {
      return {
        tableData5: [{
          id: '12987122',
          name: '好滋好味鸡蛋仔',
          category: '江浙小吃、小吃零食',
          desc: '荷兰优质淡奶，奶香浓而不腻',
          address: '上海市普陀区真北路',
          shop: '王小虎夫妻店',
          shopId: '10333',
          tag:'家'
        }, {
          id: '12987123',
          name: '好滋好味鸡蛋仔',
          category: '江浙小吃、小吃零食',
          desc: '荷兰优质淡奶，奶香浓而不腻',
          address: '上海市普陀区真北路',
          shop: '王小虎夫妻店',
          shopId: '10333',
          tag:"公司"
        }],
        // 要展开的行，数值的元素是row的key值
        expands: []
      };
    },
    methods: {
      handleect(key) {
        console.log(key);

      },
      filterTag(value, row) {
        return row.tag === value;
      },
      filterHandler(value, row, column) {
        const property = column['property'];
        return row[property] === value;
      },
      tagColor(value,row){
        switch (row.tag){
          case '家':
            return 'success';
            break;
          case '公司':
            return 'danger';
            break;
        }
      },
      //加载案件列表
      loadCases(type){

      },
      //展开案件信息
      rowClick(row, event, column) {
        Array.prototype.remove = function (val) {
          let index = this.indexOf(val);
          if (index > -1) {
            this.splice(index, 1);
          }
        };

        if (this.expands.indexOf(row.id) < 0) {
          this.expands.push(row.id);
        } else {
          this.expands.remove(row.id);
        }

      }
    }
  }
</script>

<style scoped>
  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }
</style>
