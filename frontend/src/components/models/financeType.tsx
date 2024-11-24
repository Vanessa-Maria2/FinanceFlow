import { CategoryType } from "./categoryType";

export interface FinanceType {
    id: number;
    type: string;
    amount: number;
    typeCategories: CategoryType[];
    description: string;
    date: Date;
}