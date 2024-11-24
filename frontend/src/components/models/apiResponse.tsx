export interface ApiResponse<T> {
    hasNext: boolean;
    items: T[];
    page: number;
    size: number;
    totalItems: number;
}