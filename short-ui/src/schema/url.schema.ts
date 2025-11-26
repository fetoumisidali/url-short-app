
import { z } from "zod";

export const urlSchema = z.object({
  url: z.url().trim().min(5, { error: "please enter valid url" })
});

export type UrlFormData = z.infer<typeof urlSchema>;