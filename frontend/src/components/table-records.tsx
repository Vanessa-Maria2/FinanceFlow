'use client';
import {
    Table,
    TableBody,
    TableCaption,
    TableCell,
    TableFooter,
    TableHead,
    TableHeader,
    TableRow,
  } from "@/components/ui/table"
import { useState, useEffect } from "react";
import { FinanceType } from "./models/financeType";
import { ApiResponse } from "./models/apiResponse";
import { CategoryType } from "./models/categoryType";
import { Delete02Icon, EditUser02Icon } from "hugeicons-react";
import { Button } from "@/components/ui/button"
  
  export function TableRecords() {
    const [finances, setFinances] = useState<FinanceType[]>([]);
    const [totalAmount, setTotalAmount] = useState<number>(0);

    useEffect(() => {

      const financesType = async () => {
        try {
          const response = await fetch('http://localhost:8080/finance-record');
          if(!response.ok) {
            throw new Error('Error ao buscar as finanças')
          }
          const data: ApiResponse<FinanceType> = await response.json();

          const mappedData: FinanceType[] = data.items.map((finance) => ({
            id: finance.id,
            type: finance.type,
            amount: Number(finance.amount),
            typeCategories: finance.typeCategories.map((category: CategoryType) => ({
              id: category.id,
              name: category.name,
            })),
            description: finance.description,
            date: new Date(finance.date)
          }));
          
          const total = mappedData.reduce((acc, finance) => acc + finance.amount, 0);
          setTotalAmount(total);
          setFinances(mappedData);        
        } catch(error) {
          console.error('Error: ', error);
        }
      };
      financesType();
    }, []);

    return (
      <Table>
        <TableHeader>
          <TableRow>
            <TableHead className="w-[100px]">Id</TableHead>
            <TableHead className="w-[100px]">Description</TableHead>
            <TableHead>Type</TableHead>
            <TableHead>Date</TableHead>
            <TableHead>Categories</TableHead>
            <TableHead>Amount</TableHead>
            <TableHead className="text-right">Actions</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
        {finances.map((finance) => (
            <TableRow key={finance.id}>
              <TableCell className="font-medium">{finance.id}</TableCell>
              <TableCell>{finance.description}</TableCell>
              <TableCell>{finance.type}</TableCell>
              <TableCell>{finance.date.toLocaleDateString('pt-BR')}</TableCell>
              <TableCell>{finance.typeCategories.map((category) => category.name).join(', ')}</TableCell>
              <TableCell>{finance.amount}</TableCell>
              <TableCell className="text-right">
                <Button style={{ background: 'transparent' }}>
                  <Delete02Icon 
                    size={24} 
                    color={"#d0021b"}
                  />
                </Button>
                <Button style={{ background: 'transparent' }}> 
                  <EditUser02Icon 
                    size={24} 
                    color={"#6bb833"}
                  />
                </Button>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
        <TableFooter>
          <TableRow>
            <TableCell colSpan={6}>Total</TableCell>
            <TableCell className="text-right">{totalAmount}</TableCell>
          </TableRow>
        </TableFooter>
      </Table>
    )
  }
  