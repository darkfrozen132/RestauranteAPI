﻿using System;
using System.Collections.Generic;

namespace Restaurante.Models;

public partial class TaMesa
{
    public int IdMesa { get; set; }

    public int CantidadAsientos { get; set; }

    public string Descripcion { get; set; } = null!;

    public int Reservado { get; set; }

    public int dLeft {  get; set; }

    public int dUp { get; set; }

    public virtual ICollection<TaPedido> TaPedidos { get; set; } = new List<TaPedido>();
}
