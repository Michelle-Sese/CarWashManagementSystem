function renderExpense(){
    TracomAcademy.Grid.apply({
        contentRender: "content",
        gridTitle: "Expense",
        componentId: "expense",
        url: "expense",
        columns: [{
            header: "Id",
            dataIndex: "id",
            width: 20
        }, {
            header: "Type_id",
            dataIndex: "Type_id",
            width: 30
        }, {
            header: "Amount",
            dataIndex: "Amount",
            width: 30
            },

        {
            header: "",
            dataIndex: "delete",
            width: 15,
            },
        {
            header: "",
            dataIndex: "edit",
            width: 15,
        },
        ],
        store: [],
        form: {
            id: "expense-form",
            url: "expense",
            items: [{
                label: "ID No.",
                name: "eid",
                id: "expense.eid",
                type: "text"
            },{
                label: "Type_id",
                name: "eType_id",
                id: "expense.eType_id",
                type: "text"
            },{
                label: "Amount",
                name: "eamount",
                id: "expense.eAmount",
                type: "text"
              }]

        }
    });
}
