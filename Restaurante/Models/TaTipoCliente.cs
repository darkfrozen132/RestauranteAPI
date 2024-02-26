using System;
using System.Collections.Generic;

namespace Restaurante.Models;

public partial class TaTipoCliente
{
    public int IdTipoCliente { get; set; }

    public int Porcentaje { get; set; }

    public virtual ICollection<TaCliente> TaClientes { get; set; } = new List<TaCliente>();
}
