'use client';
import { Button } from "@/components/ui/button"
import React, { useState, useEffect } from "react";
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
import { Select, SelectContent, SelectItem, SelectTrigger, SelectGroup, SelectLabel, SelectValue } from "./ui/select";
import { CalendarIcon } from "lucide-react";
import { CategoryType } from "./models/categoryType";
import { Calendar } from "@/components/ui/calendar";
import {
  Popover,
  PopoverContent,
  PopoverTrigger,
} from "@/components/ui/popover"
import { format } from "date-fns"
import { cn } from "@/lib/utils"


export function DialogCreateRecord() {
  const [value, setValue] = useState("");
  const [isIncome, setIsIncome] = useState(true);
  const [categoryTypes, setCategoryTypes] = useState<CategoryType[]>([]);
  const [selectedCategories, setSelectedCategories] = useState<CategoryType[]>([]);
  const [date, setDate] = React.useState<Date>()
  const [description, setDescription] = useState("");

  useEffect(() => {
    const fetchCategoryTypes = async () => {
      try {
        const response = await fetch('http://localhost:8080/type-category');
        if (!response.ok) {
          throw new Error('Erro ao buscar tipos de categoria');
        }
        const data: CategoryType[] = await response.json();
        setCategoryTypes(data.map((category) => ({ id: category.id, name: category.name })));
      } catch (error) {
        console.error('Erro:', error);
      }
    };

    fetchCategoryTypes();
  }, []);

  const handleSelectCategory = (category: CategoryType) => {
    setSelectedCategories((prev) => {
      const isSelected = prev.some((c) => c.id === category.id);
      if (isSelected) {
        return prev.filter((c) => c.id !== category.id);
      } else {
        return [...prev, category];
      }
    });
  };

  const handleSubmit = async () => {
    const record = {
      amount: parseFloat(value),
      type: isIncome ? "INCOME" : "EXPENSE",
      data: date,
      typeCategories: selectedCategories,
      description: description
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
                onChange={(e) => setValue(e.target.value)} />
            </div>
          </TabsContent>
          <TabsContent value="password">
            <div className="space-y-1 flex items-center gap-3">
              <Label htmlFor="value">R$</Label>
              <Input id="value" type="real" placeholder="10.00" value={value}
                onChange={(e) => setValue(e.target.value)} />
            </div>
          </TabsContent>
          <Select>
            <SelectTrigger className="w-[180px]">
              <SelectValue placeholder="Select a type category"> {selectedCategories.length > 0
                ? selectedCategories.map((category) => category.name).join(', ')  // Exibe as categorias separadas por vírgula
                : "Select a type category"}</SelectValue>
            </SelectTrigger>
            <SelectContent>
              <SelectGroup>
                <SelectLabel>Categories</SelectLabel>
                {categoryTypes.map((category) => (
                  <SelectItem
                    key={category.id}
                    value={category.id.toString()}
                    onClick={() => handleSelectCategory(category)}
                  >
                    {category.name}
                  </SelectItem>
                ))}
              </SelectGroup>
            </SelectContent>
          </Select>
          <Popover>
            <PopoverTrigger asChild>
              <Button
                variant={"outline"}
                className={cn(
                  "w-[280px] justify-start text-left font-normal",
                  !date && "text-muted-foreground"
                )}
              >
                <CalendarIcon className="mr-2 h-4 w-4" />
                {date ? format(date, "PPP") : <span>Pick a date</span>}
              </Button>
            </PopoverTrigger>
            <PopoverContent className="w-auto p-0 popover-content pointer-events-auto">
              <Calendar
                mode="single"
                selected={date}
                onSelect={setDate}
                initialFocus
              />
            </PopoverContent>
          </Popover>

          <div className="space-y-1 flex items-center gap-3">
            <Label htmlFor="description">Description</Label>
            <Input id="description" type="text" placeholder="Digite..." value={description}
              onChange={(e) => setDescription(e.target.value)} />
          </div>
        </Tabs>

        <Button onClick={handleSubmit}>Create</Button>
      </DialogContent>
    </Dialog>
  )
}
