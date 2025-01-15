import {ReactNode} from "react";

export type BaseType = {
    children: ReactNode;
}

export type condEqType = {
    menu: string;
    qntty: number;
}

export type condType = {
    condEqList: condEqType[]
}

export type ApiResponseType = {
    success: boolean
    message: string
    rtnModel: any
}

export type orderReceivedType = {
    orderNo: number;
    menu: string;
    qntty: number;
    ordDt: string;
}
