<template>
  <a-button
    @click="router.push({ path: '/add/question' })"
    shape="round"
    type="primary"
    >创建题目
  </a-button>
  <a-divider />
  <div id="manageQuestionView">
    <a-table
      :ref="tableRef"
      :columns="columns"
      :data="dataList"
      :pagination="pagination"
      @page-change="onPageChange"
    >
      <template #tags="{ record }">
        <a-space wrap>
          <a-tag
            v-for="(tag, index) of JSON.parse(record.tags)"
            :key="index"
            color="green"
            >{{ tag }}
          </a-tag>
        </a-space>
      </template>
      <template #createTime="{ record }">
        <!--{{ moment(record.createTime).format("YYYY-MM-DD") }}-->
        {{ record.createTime.split("T")[0] }}
      </template>
      <template #optional="{ record }">
        <a-space>
          <a-button type="primary" shape="round" @click="doUpdate(record)">
            修改</a-button
          >
          <a-button status="danger" shape="round" @click="doDelete(record)"
            >删除</a-button
          >
        </a-space>
      </template>
    </a-table>
  </div>
</template>

<script setup lang="ts">
import { onMounted, reactive, ref, watchEffect } from "vue";
import { Question, QuestionControllerService } from "../../../generated";
import message from "@arco-design/web-vue/es/message";
import { useRouter } from "vue-router";
import moment from "moment/moment";

const tableRef = ref();

const dataList = ref([]);
const total = ref<number>(0);
const searchParams = ref({
  pageSize: 10,
  current: 1,
});
const pagination = reactive({
  ...searchParams.value,
  showTotal: true,
  total: total,
  showJumper: true,
});
const onPageChange = (current: number) => {
  pagination.current = current;
  searchParams.value.current = current;
  loadData();
};
const loadData = async () => {
  const res = await QuestionControllerService.listManageQuestionByPageUsingPost(
    searchParams.value
  );
  if (res.code === 0) {
    dataList.value = res.data.records;
    total.value = res.data.total;
  } else {
    message.error("加载失败，" + res.message);
  }
};

/**
 * 监听 searchParams 变量，改变时触发页面的重新加载
 */
watchEffect(() => {
  loadData();
});

/**
 * 页面加载时，请求数据
 */
onMounted(() => {
  loadData();
});

const columns = [
  {
    title: "id",
    dataIndex: "id",
    width: 200,
  },
  {
    title: "标题",
    dataIndex: "title",
    ellipsis: true,
    tooltip: true,
    width: 150,
  },
  {
    title: "标签",
    dataIndex: "tags",
    slotName: "tags",
    width: 150,
  },
  {
    title: "提交数",
    dataIndex: "submitNum",
    width: 100,
  },
  {
    title: "通过数",
    dataIndex: "acceptedNum",
    width: 100,
  },
  {
    title: "判题配置",
    dataIndex: "judgeConfig",
    children: [
      {
        title: "时间限制",
        dataIndex: "timeLimit",
        width: 100,
      },
      {
        title: "内存限制",
        dataIndex: "memoryLimit",
        width: 100,
      },
      {
        title: "堆栈限制",
        dataIndex: "stackLimit",
        width: 100,
      },
    ],
    width: 300,
  },
  {
    title: "创建用户",
    dataIndex: "userName",
    ellipsis: true,
    tooltip: true,
    width: 100,
  },
  {
    title: "创建时间",
    dataIndex: "createTime",
    width: 170,
  },
  {
    title: "操作",
    slotName: "optional",
    width: 170,
  },
];

const doDelete = async (question: Question) => {
  const res = await QuestionControllerService.deleteQuestionUsingPost({
    id: question.id,
  });
  if (res.code === 0) {
    message.success("删除成功");
    await loadData();
  } else {
    message.error(res.msg);
  }
};

const router = useRouter();

const doUpdate = (question: Question) => {
  router.push({
    path: "/update/question",
    query: {
      id: question.id,
    },
  });
};
</script>

<style scoped>
#manageQuestionView {
}
</style>
