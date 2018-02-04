<template>
  <el-container style="height: 800px; border: 1px solid #eee">
    <el-aside width="20%">
      <span>案件列表</span>
      <el-menu :default-openeds="['1','2','3']">
        <el-submenu index="1">
          <template slot="title"><i class="el-icon-message"></i>发诉案件</template>
          <el-menu-item v-for="oneifcaselist in anjianlist" :index="oneifcaselist.indexxx" :key=oneifcaselist.id
                        @click="caseInfoLoad(oneifcaselist.caseId)">
            {{oneifcaselist.caseNum}}
          </el-menu-item>
        </el-submenu>
      </el-menu>
    </el-aside>
    <el-container>
      <el-header height="200px">
        <el-form ref="caseInfoForm" :model="caseInfoForm" label-width="100px" size="mini" :inline="true">
          <el-form-item label="案号">
            <el-input v-model="caseInfoForm.caseNum" style="width: 140%;"></el-input>
          </el-form-item>

          <el-form-item label="案由">
            <el-select v-model="caseInfoForm.anyou" placeholder="请输入案由" filterable>
              <el-option label="合同纠纷ht" value="合同纠纷"></el-option>
              <el-option label="民间借贷纠纷mjjd" value="民间借贷纠纷"></el-option>
              <el-option label="金融借款合同纠纷jrjkht" value="金融借款合同纠纷"></el-option>
              <el-option label="信用卡纠纷xyk" value="信用卡纠纷"></el-option>
              <el-option label="执行异议zxyy" value="执行异议"></el-option>
              <el-option label="申请诉前财产保全sqbq" value="申请诉前财产保全"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="立案日期">
            <el-date-picker
              v-model="caseInfoForm.liandate"
              type="date"
              placeholder="选择日期"
            >
            </el-date-picker>
          </el-form-item>
          <el-form-item label="开庭日期">
            <el-date-picker
              v-model="caseInfoForm.kaitingdate"
              type="datetime"
              placeholder="选择日期"
            >
            </el-date-picker>
          </el-form-item>
          <el-form-item label="是否保全">
            <el-switch
              v-model="caseInfoForm.havePreservation"
              active-text="有保全"
              inactive-text="无保全"
              @change="casePreservationChange">
            </el-switch>
            <el-button type="text" size="small" @click="createbaoquandoc()">保全文书</el-button>
          </el-form-item>
          <el-form-item>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="addCase">保存</el-button>
          </el-form-item>
          <el-dialog title="案件保全信息" :visible.sync="casePreservationInfoFormVisible">
            <el-form ref="casePreservationInfo" :model="casePreservationInfo" size="mini" :inline="true">
              <el-form-item label="申请人">
                <el-select v-model="casePreservationInfo.shenqingren" placeholder="请选择当事人" multiple>
                  <el-option v-for="(onelp, index) in this.tableData" :label="onelp.name"
                             :key="index" :value="onelp.name">
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="于">
                <el-date-picker type="date" v-model="casePreservationInfo.shenqingdate" placeholder="选择日期">
                </el-date-picker>
              </el-form-item>
              <el-form-item label="向本院申请财产保全，请求依法冻结被申请人">
                <el-select v-model="casePreservationInfo.beishenqingren" placeholder="请选择当事人" multiple>
                  <el-option v-for="(onelp, index) in this.tableData" :label="onelp.name"
                             :key="index" :value="onelp.name">
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="银行存款">
                <el-input v-model="casePreservationInfo.shenqingbiaodi" placeholder="请输入内容"></el-input>
              </el-form-item>
              <el-form-item label="万元或查封同等价值的财产。">
                <el-input v-model="casePreservationInfo.danbaocaichan" placeholder="请输入担保信息"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="saveCasePreservationInfo()">保存</el-button>
              </el-form-item>
            </el-form>
          </el-dialog>

        </el-form>

      </el-header>
      <el-main height="100%">
        <el-table
          :data="tableData"
          style="width: 100%"
          :row-class-name="tableRowClassName">
          <el-table-column
            prop="type"
            label="诉讼地位"
            width="80">
          </el-table-column>
          <el-table-column
            prop="name"
            label="名称"
            width="170"
          >
          </el-table-column>
          <el-table-column
            prop="lawsPartType"
            label="类型"
            width="80">
          </el-table-column>
          <el-table-column
            prop="phone"
            label="联系电话"
            width="120">
          </el-table-column>
          <el-table-column
            label="代理人信息"
            width="100">
            <template slot-scope="scope">
              <el-popover trigger="hover" placement="top">
                <p>姓名: {{ scope.row.litiRepresInfo }}</p>
                <div slot="reference" class="name-wrapper">
                  <el-tag size="medium">代理人信息</el-tag>
                </div>
              </el-popover>
            </template>
          </el-table-column>
          <el-table-column
            prop="addr"
            label="地址"
            width="300">
          </el-table-column>
          <el-table-column
            fixed="right"
            label="操作"
            width="170px">
            <template slot-scope="scope">
              <el-button @click="handleClick(scope.row)" type="text" size="small">编辑</el-button>
              <el-button type="text" size="small" @click="handleDelSdr(scope.row)">删除</el-button>
              <el-button type="text" size="small" @click="createfasudoc(scope.row)">发诉文书</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-button type="success" icon="el-icon-plus" @click="addLitiParts('原告')" plain>原告</el-button>
        <el-button type="warning" icon="el-icon-plus" @click="addLitiParts('被告')" plain>被告</el-button>
        <el-button type="info" icon="el-icon-plus" @click="addLitiParts('第三人')" plain>第三人</el-button>
      </el-main>
      <el-dialog title="添加当事人信息" :visible.sync="litiPartsInfoFormVisible">
        <el-form ref="litiPartsInfoForm" :model="litiPartsInfoForm" label-width="100px" size="mini" :inline="true">

          <el-form-item label="当事人类型">
            <el-select v-model="selectLawsPartType" placeholder="请选择当事人类型" @change="litiPartsLawsPartTypeChange">
              <el-option label="公民" value="公民"></el-option>
              <el-option label="法人" value="法人"></el-option>
              <el-option label="其他组织" value="其他组织"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="诉讼地位">
            <el-select v-model="litiPartsInfoForm.type" placeholder="请选择诉讼地位">
            </el-select>
          </el-form-item>
          <el-form-item label="名称" style="width: 300px;">
            <el-input v-model="litiPartsInfoForm.name"></el-input>
          </el-form-item>
          <el-form-item label="联系电话" style="width: 300px;">
            <el-input v-model="litiPartsInfoForm.phone"></el-input>
          </el-form-item>
          <el-form-item label="住址" style="width: 300px;">
            <el-input v-model="litiPartsInfoForm.addr"></el-input>
          </el-form-item>
          <el-form-item label="公民身份号码" style="width: 300px;">
            <el-input v-model="litiPartsInfoForm.gmsfhm" v-bind:disabled="!lawsPartTypeIsPson"
                      @blur="getCsrqSex"></el-input>
          </el-form-item>
          <el-form-item label="民族" style="width: 300px;">
            <el-input v-model="litiPartsInfoForm.minzu" v-bind:disabled="!lawsPartTypeIsPson"></el-input>
          </el-form-item>
          <el-form-item label="性别" style="width: 300px;">
            <el-input v-model="litiPartsInfoForm.sex" v-bind:disabled="!lawsPartTypeIsPson"></el-input>
          </el-form-item>
          <el-form-item label="出生日期">
            <el-date-picker
              v-model="litiPartsInfoForm.csrq"
              type="date"
              v-bind:disabled="!lawsPartTypeIsPson"
              placeholder="选择日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="信用代码" style="width: 300px;">
            <el-input v-model="litiPartsInfoForm.tyshxydm" v-bind:disabled="lawsPartTypeIsPson"></el-input>
          </el-form-item>
          <el-form-item label="法定代表人" style="width: 700px;">
            <el-input v-model="litiPartsInfoForm.fddbr" v-bind:disabled="lawsPartTypeIsPson"
                      style="width: 500px;"></el-input>
          </el-form-item>
          <!--<el-form-item label="代理人信息" style="width: 700px;">-->
          <!--<el-input v-model=""style="width: 500px;"></el-input>-->
          <!--</el-form-item>-->

          <el-form-item>
            <el-button type="primary" @click="addLP">保存</el-button>
            <el-button @click="resetLpf">取消</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>

      <el-footer direction="horizontal" height="200px">
        <date-calculator class="test"></date-calculator>
      </el-footer>
    </el-container>
    <el-aside width="30%" >
      <el-header height = "200">
        <a>记事</a>
        <el-input
          type="textarea"
          :rows="5"
          placeholder="请输入内容"
          v-model="newLogs">
        </el-input>
        <el-button type="primary" @click="saveCaseLog">保存</el-button>
      </el-header>
      <div height = "600">
        <!--<el-steps direction="vertical" :active=caseLogsData.length-1>-->
          <!--<el-step-->
            <!--v-for="(caseLog,index) in caseLogsData"-->
            <!--:description="caseLog.logDeteil"-->
            <!--:title="caseLog.date+''"-->
            <!--:key="index"-->
          <!--&gt;sdfsdfasfsdf</el-step>-->
        <!--</el-steps>-->
        <el-table
          border
          :data="caseLogsData"
          >
          <el-table-column
            prop="date"
            label="日期"
            width="180">
          </el-table-column>

          <el-table-column
            prop="logDeteil"
            label="纪要">
          </el-table-column>
        </el-table>
      </div>

    </el-aside>
  </el-container>
</template>

<script>
  import DateCalculator from './DateCalculator';

  export default {
    components: {DateCalculator},
    created() {
      this.$api.get("allCase", null, r => {
        this.anjianlist = r;
      });
    },

    methods: {
      caseInfoLoad(caseId) {
        this.$api.get("getCaseByid", {'caseId': caseId}, r => {
          this.caseInfoForm = r;
          this.currentCaseId = r.caseId;
          this.tableData = r.litiParts;
        });
        this.getCaseLog(caseId)
      },
      getCaseLog(caseId){
        this.$api.get("getCaseLogs", {'caseId': caseId}, r => {
          this.caseLogsData = r;
        })
      },
      saveCaseLog(){
        this.$api.post("saveCaseLogs", {'caseId': this.currentCaseId,'logDeteil': this.newLogs,'date' : new Date()}, r => {
          this.getCaseLog(this.currentCaseId);
          this.newLogs = '';
        })
      },
      tableRowClassName({row, rowIndex}) {
        if (row.type === "原告") {
          return 'success-row';
        } else if (row.type === "被告") {
          return 'warning-row';
        }
        return '';
      },
      createbaoquandoc(){
        this.$api.file({
          "caseId": this.currentCaseId,
          "lawsDocType": 4
        });
      },
      createfasudoc(row) {
        console.log(row)
        this.$api.file({
          "caseId": row.caseId,
          "litiPartId": row.litiPartId,
          "lawsDocType": 3
        });
      },
      handleClick(row) {
        this.litiPartsInfoFormVisible = true;
        this.litiPartsInfoForm.name = row.name;
        console.log(row);
      },
      handleDelSdr(row) {
        console.log(this.tableData.pop());
      },
      onSubmit() {
        console.log();
      },
      addLitiParts(dsrlx) {
        this.litiPartsInfoFormVisible = true;
        this.litiPartsInfoForm = {sex: "男", csrq: new Date()};
        this.litiPartsInfoForm.type = dsrlx;
      },
      getCsrqSex() {
        var gmsfhm = this.litiPartsInfoForm.gmsfhm;
        var birthdayno, birthdaytemp
        if (gmsfhm.length == 18) {
          birthdayno = gmsfhm.substring(6, 14)
        } else if (gmsfhm.length == 15) {
          birthdaytemp = gmsfhm.substring(6, 12)
          birthdayno = "19" + birthdaytemp
        } else {
          return;
        }
        var birthday = birthdayno.substring(0, 4) + "-" + birthdayno.substring(4, 6) + "-" + birthdayno.substring(6, 8);
        this.litiPartsInfoForm.csrq = new Date(birthday);
        if (parseInt(gmsfhm.substr(16, 1)) % 2 == 1) {
          this.litiPartsInfoForm.sex = "男";
        } else {
          this.litiPartsInfoForm.sex = "女";
        }
      },
      addCase() {
        var inputCaseNum = parseInt(this.caseInfoForm.caseNum, 10);
        if (this.$utils.isRealNum(inputCaseNum)) {
          inputCaseNum = "（2018）鲁0202民初" + inputCaseNum + "号";
          this.caseInfoForm.caseNum = inputCaseNum;
        }
        this.$api.post("saveCase", this.caseInfoForm, r => {
          //console.log(r)
          this.$api.get("allCase", null, r => {
            this.anjianlist = r;
          });
        });
      },
      casePreservationChange() {//案件保全
        if (!this.caseInfoForm.havePreservation) {
          this.casePreservationInfoFormVisible = true;
        }
      },
      saveCasePreservationInfo() {
        var sqdate = this.casePreservationInfo.shenqingdate;
        var bsqrs = this.casePreservationInfo.beishenqingren;
        var bsqrxx = '';
        for (var bsqr in bsqrs) {
          bsqrxx = bsqrxx + '被申请人' + bsqrs[bsqr] + "、"
        }
        bsqrxx = bsqrxx.slice(0, -1);
        var date = sqdate.getFullYear() + '年' + (sqdate.getMonth() + 1) + '月' + sqdate.getDate() + "日";
        var detail = '申请人' + this.casePreservationInfo.shenqingren + '于' + date +
          '向本院申请财产保全，请求依法冻结' + bsqrxx + '银行存款' + this.casePreservationInfo.shenqingbiaodi
          + '万元或查封同等价值的财产。' + this.casePreservationInfo.danbaocaichan + '。'
        var caidingzhuwen = '冻结' + bsqrxx + '银行存款' + this.casePreservationInfo.shenqingbiaodi
          + '万元或查封、扣押同等价值的其他财产。';
        this.caseInfoForm.casePreservationInfoDetil = detail;
        this.caseInfoForm.casePreservationcaiding = caidingzhuwen;
        this.caseInfoForm.havePreservation = true;
        this.casePreservationInfoFormVisible = false;
        addCase();

      },
      addLP() {
        this.litiPartsInfoForm.lawsPartType = this.selectLawsPartType;
        this.litiPartsInfoForm.caseId = this.currentCaseId;
        this.$api.post('addlitipart', this.litiPartsInfoForm, r => {
          this.litiPartsInfoFormVisible = false;
          this.caseInfoLoad(this.currentCaseId)
        })
      },
      resetLpf() {
        // this.$refs["litiPartsInfoForm"].resetFields();
        this.litiPartsInfoForm = {};
        this.litiPartsInfoFormVisible = false;
      }
      ,
      litiPartsLawsPartTypeChange() {
        this.litiPartsInfoForm.lawsPartType = this.selectLawsPartType;
        if (this.selectLawsPartType === "公民") {
          this.litiPartsInfoForm.isPson = true;
          this.lawsPartTypeIsPson = true;
        } else {
          this.litiPartsInfoForm.isPson = false;
          this.lawsPartTypeIsPson = false;
        }
        console.log(this.litiPartsInfoForm.lawsPartType);
      }
      ,
      checkList: ['选中且禁用', '复选框 A']
    },
    data() {
      return {
        casePreservationInfo: {
          shenqingren: '',
          shenqingdate: new Date(),
          beishenqingren: [],
          shenqingbiaodi: 0,
          danbaoren: '申请人',
          danbaocaichan: ''
        },
        currentCaseId: '',
        caseLogsData: [],
        stepActive: 5,
        newLogs:'',


        tableData:[],
        dialogTableVisible: false,
        litiPartsInfoFormVisible: false,
        casePreservationInfoFormVisible: false,
        labelPosition: 'right',
        selectLawsPartType: '公民',
        lawsPartTypeIsPson: true,
        litiPartsInfoForm: {sex: "男", csrq: new Date()},
        formLabelWidth: '120px',
        caseInfoForm: {
          caseNum: '',
          anyou: '',
          lianDate: '',
          kaitingDate: '',
          perservationInfoDetail: '',
          havePreservation: false
        },
        activeName: 'first',

        anjianlist: [],
        stepDatas: [
          {description: 'jsifjsjfosj', title: "1"},
          {description: 'sdfsdf', title: "步骤er"},
          {description: 'sdfsdf', title: "步骤3"},
          {description: 'sdfsdf', title: "步骤4"},
          {description: 'sdfsdf', title: "步骤6"},
          {description: 'sdfsdf', title: "步骤5"},
        ],



      }
    }

  }
</script>

<style>
  .el-table .warning-row {
    background: oldlace;
  }

  .el-table .success-row {
    background: #f0f9eb;
  }

  .test {

    background-color: #f2f2f2;
  }

</style>
