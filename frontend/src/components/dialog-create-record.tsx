'use client';
import { Button } from "@/components/ui/button"
import React, { useState } from "react";
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "@/components/ui/dialog"
import { ButtonCreateRecord } from '@/components/button-create-record'
import { Tabs, TabsContent, TabsList, TabsTrigger } from "./ui/tabs"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"


export function DialogCreateRecord() {
  const [value, setValue] = useState("");
  const [isIncome, setIsIncome] = useState(true);

  const handleSubmit = async () => {
    const record = {
      amount: parseFloat(value),
      type: isIncome ? "INCOME" : "EXPENSE",
      data: new Date(),
    };

    try {
      const response = await fetch('http://localhost:8080/finance-record', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(record),
      });

      if (!response.ok) {
        throw new Error('Erro ao cadastrar o registro');
      }

      const result = await response.json();
      console.log('Registro cadastrado com sucesso:', result);
      setValue(""); 
    } catch (error) {
      console.error('Erro:', error);
    }
  };

  return (
    <Dialog>
      <DialogTrigger asChild>
        <ButtonCreateRecord />
      </DialogTrigger>
      <DialogContent className="sm:max-w-[525px]">
        <DialogHeader>
          <DialogTitle>Register</DialogTitle>
          <DialogDescription>
            Create new finance record
          </DialogDescription>
        </DialogHeader>
        <Tabs defaultValue="account" className="grid gap-4 py-4">
            <TabsList className="grid w-full grid-cols-2">
                <TabsTrigger value="account" className="text-teal-300" onClick={() => setIsIncome(true)}>
                    <p className="text-teal-500">INCOME</p>
                </TabsTrigger>
                <TabsTrigger value="password" onClick={() => setIsIncome(false)}>
                    <p className="text-rose-500">EXPENSE</p>
                </TabsTrigger>
            </TabsList>
            <TabsContent value="account">
                <div className="space-y-1 flex items-center gap-3">
                    <Label htmlFor="value">R$</Label>
                    <Input id="value" type="real" placeholder="10.00" value={value}
                      onChange={(e) => setValue(e.target.value)}/>
                </div>
            </TabsContent>
            <TabsContent value="password">
            <div className="space-y-1 flex items-center gap-3">
                    <Label htmlFor="value">R$</Label>
                    <Input id="value" type="real" placeholder="10.00" value={value}
                      onChange={(e) => setValue(e.target.value)}/>
                </div>
            </TabsContent>
        </Tabs>

        <Button onClick={handleSubmit}>Create</Button>
      </DialogContent>
    </Dialog>
  )
}
